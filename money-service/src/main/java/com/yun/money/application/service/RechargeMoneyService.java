package com.yun.money.application.service;

import com.yun.common.*;
import com.yun.common.anotation.UseCase;
import com.yun.money.adapter.axon.command.AddMoneyRequestCreateCommand;
import com.yun.money.adapter.out.service.openbanking.PrepareMoneyTransferRequest;
import com.yun.money.adapter.out.service.openbanking.factory.OpenbankingModelFactory;
import com.yun.money.application.port.factory.RechargeDomainFactory;
import com.yun.money.application.port.in.RechargeMoneyAmountCommand;
import com.yun.money.application.port.in.RechargeMoneyUseCase;
import com.yun.money.application.port.out.GetMembershipForMoneyPort;
import com.yun.money.application.port.out.IncreaseMoneyAmountPort;
import com.yun.money.application.port.out.SendRechargingMoneyTaskPort;
import com.yun.money.application.port.out.client.banking.GetRegisteredBankAccountPort;
import com.yun.money.application.port.out.client.openbanking.OpenbankingForMoneyPort;
import com.yun.money.domain.MemberMoneyWallet;
import com.yun.money.domain.PayWalletMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class RechargeMoneyService implements RechargeMoneyUseCase {

    private final IncreaseMoneyAmountPort increaseMoneyAmountPort;
    private final CountDownLatchManager countDownLatchManager;
    private final GetMembershipForMoneyPort getMembershipForMoneyPort;
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;
    private final OpenbankingForMoneyPort openbankingForMoneyPort;
    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;
    private final CommandGateway commandGateway;

    @Override
    public PayWalletMoney rechargingMoneyToSeobetterpay(RechargeMoneyAmountCommand command) {
        //증액(충전)
        //1. TODO: 멤버십 회원 검증 -> spring security(캐싱)
        getMembershipForMoneyPort.getMembership(command.getMembershipId());
        //1-1 고객의 연동된 계좌가 있는지 + 정상여부 체크
        getRegisteredBankAccountPort.getLinkedBankAccounts(command.getMembershipId());
        //1.1 고객 요청 정보 데이터 money 요청 히스토리 저장 (계산하는 검증 로직 필요) (요청 상태로 저장)
        PayWalletMoney payWalletMoney
                = increaseMoneyAmountPort.increaseMoneyAmount(RechargeDomainFactory.newPayWalletMoney(command));
        //1-2 TODO: 페이 법인 계좌 상태 정상 여부 체크 및 입출금 가능 여부 체크(외부 API)
        //2. TODO: openbanking request 생성
        PrepareMoneyTransferRequest prepareMoneyTransferRequest = OpenbankingModelFactory.newInstance(command);
        //openbankingForMoneyPort.requestOpenbankingService(prepareMoneyTransferRequest);

        return payWalletMoney;
        //3. TODO: openbanking-service 모듈로 요청 / 펌뱅킹 (고객의 연동된 계좌 -> 법인 계좌) 계좌이체
        //4. 정상/실패 예외 및 정상 처리
        //결과가 정상적이라면 성곡으로 moneyChargingRequest 상태값을 변동 후에 리턴
        //성공 시에 멤버의 memberMoney 값 증액이 필요

    }

    @Override
    public PayWalletMoney rechargingMoneyToSeobetterpayAsync(RechargeMoneyAmountCommand command) {
        //subtask
        //각 서비스에 특정 membershipId로 validation을 하기 위한 task
        //1. subtask, task
        //domain 생성
        SubTask validMemberTask = new SubTask(
                command.getMembershipId(),
                "validMemberTask: 멤버십 유효성 검사",
                MoneyTaskType.MEMBERSHIP,
                "ready"
        );
        //banking sub task
        //banking account validation
        SubTask validBankingAccountTask = new SubTask(
                command.getMembershipId(),
                "validMemberTask: 멤버십 유효성 검사",
                MoneyTaskType.BANKING,
                "ready"
        );
        //amount money firm banking --> 외부 API
        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(validMemberTask);
        subTaskList.add(validBankingAccountTask);

        RechargingMoneyTask rechargingMoneyTask = RechargingMoneyTask.builder()
                .taskId(UUID.randomUUID().toString())
                .taskName("Increase money task / 머니 충전 task")
                .membershipId(command.getMembershipId())
                .subTaskList(subTaskList)
                .toBankName("seoBetterPay")
                .toBankAccountNumber(command.getLinkedBankAccountNumber())
                .moneyAmount(command.getRechargeAmount())
                .build();

        log.info("RechargingMoneyTask: {}", rechargingMoneyTask);

        /*RechargingMoneyTask task = new RechargingMoneyTask(
                UUID.randomUUID().toString(),
                "Increase money task / 머니 충전 task",
                command.getTargetMembershipId(),
                subTaskList,
                "seoBetterPay",
                command.getBankAccountNumber(),
                command.getRequestAdjustAmount()
        );*/

        //2. kafka cluster produce
        //task produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(rechargingMoneyTask);
        countDownLatchManager.addCountDownLatch(rechargingMoneyTask.getTaskId());

        //3. wait
        try {
            countDownLatchManager.getCountDownLatch(rechargingMoneyTask.getTaskId()).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //3-1. task-consumer에서 수행
        //등록된 sub-task, status 모두 ok -> task 결과를 produce

        //4. task result consume
        //받은 응답을 다시 countDownLatchManager를 통해서 결과 데이터를 받아야한다
        String result = countDownLatchManager.getDataForKey(rechargingMoneyTask.getTaskId());
        log.info("result: {}", result);
        if (result.equals("SUCCESS")) {
            //4-1. consume ok, logic
            return increaseMoneyAmountPort.increaseMoneyAmount(RechargeDomainFactory.newPayWalletMoney(command));
        } else {
            //4-2. consume fail, logic
            return null;
        }
        //5. consume ok, Logic
    }

    @Override
    public void addMoneyToSeobetterpayByEvent(MemberMoneyWallet memberMoneyWallet, RechargeMoneyAmountCommand command) {
        AddMoneyRequestCreateCommand addMoneyRequestCreateCommand = new AddMoneyRequestCreateCommand(
                memberMoneyWallet.getMoneyAggregateIdentifier(),
                UUID.randomUUID().toString(),
                memberMoneyWallet.getMembershipId(),
                memberMoneyWallet.getMoneyTotalAmount());

        commandGateway.send(addMoneyRequestCreateCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.info("throwable: {}", throwable);
            }

            //increase money sourcing 완료 -> money increase request
            log.info("increase money result: {}", result.toString());
            increaseMoneyAmountPort.increaseMoneyAmount(RechargeDomainFactory.newPayWalletMoney(command));
        });

        /*//axon command 생성
        IncreaseMemberMoneyCommand increaseMemberMoneyCommand = new IncreaseMemberMoneyCommand(
                memberMoneyWallet.getMoneyAggregateIdentifier(),
                memberMoneyWallet.getMembershipId(),
                command.getIncreaseAmount());

        //saga의 시작을 나타내는 command
        commandGateway.send(increaseMemberMoneyCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.info("throwable: {}", throwable);
            }

            //increase money sourcing 완료 -> money increase request
            log.info("increase money result: {}", result);
            increaseMoneyAmountPort.increaseMoneyAmount(command.toPayWalletMoney(MoneyAdjustingResultStatus.SUCCEEDED));
        });*/
    }
}

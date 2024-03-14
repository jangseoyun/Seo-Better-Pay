package com.yun.money.application.service;

import com.yun.common.MoneyTaskType;
import com.yun.common.RechargingMoneyTask;
import com.yun.common.SubTask;
import com.yun.common.UseCase;
import com.yun.money.adapter.in.web.model.MoneyChangingResultStatus;
import com.yun.money.application.port.in.AdjustMoneyUseCase;
import com.yun.money.application.port.in.DecreaseMoneyAmountCommand;
import com.yun.money.application.port.in.IncreaseMoneyAmountCommand;
import com.yun.money.application.port.out.DecreaseMoneyAmountPort;
import com.yun.money.application.port.out.GetMembershipForMoneyPort;
import com.yun.money.application.port.out.IncreaseMoneyAmountPort;
import com.yun.money.application.port.out.SendRechargingMoneyTaskPort;
import com.yun.money.domain.MoneyAmountCalculator;
import com.yun.money.domain.PayWalletMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class AdjustMoneyAmountService implements AdjustMoneyUseCase {

    private final GetMembershipForMoneyPort getMembershipForMoneyPort;
    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;
    private final IncreaseMoneyAmountPort increaseMoneyAmountPort;
    private final DecreaseMoneyAmountPort decreaseMoneyAmountPort;
    private final MoneyAmountCalculator moneyAmountCalculator;

    @Override
    public PayWalletMoney increaseMoneyAmountRequest(IncreaseMoneyAmountCommand command) {
        //증액(충전)
        //1. TODO: 멤버십 회원 검증
        getMembershipForMoneyPort.getMembership(command.getTargetMembershipId());
        //1-1 고객의 연동된 계좌가 있는지 + 정상여부 체크
        //1-2 페이 법인 계좌 상태 정상 여부 체크 및 입출금 가능 여부 체크
        //2. TODO: money 요청 히스토리 저장 (계산하는 검증 로직 필요) (요청 상태로 저장)
        return increaseMoneyAmountPort.increaseMoneyAmount(command.toPayWalletMoney(MoneyChangingResultStatus.SUCCEEDED));
        //3. TODO: 펌뱅킹 (고객의 연동된 계좌 -> 법인 계좌)
        //4. 정상/실패 예외 및 정상 처리
    }

    @Override
    public PayWalletMoney increaseMoneyAmountRequestAsync(IncreaseMoneyAmountCommand command) {
        //subtask
        //각 서비스에 특정 membershipId로 validation을 하기 위한 task
        //1. subtask, task
        SubTask validMemberTask = new SubTask(
                command.getTargetMembershipId(),
                "validMemberTask: 멤버십 유효성 검사",
                MoneyTaskType.MEMBERSHIP,
                "ready"
        );
        //banking sub task
        //banking account validation
        SubTask validBankingAccountTask = new SubTask(
                command.getTargetMembershipId(),
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
                .membershipId(command.getTargetMembershipId())
                .subTaskList(subTaskList)
                .toBankName("seoBetterPay")
                .toBankAccountNumber(command.getBankAccountNumber())
                .moneyAmount(command.getRequestAdjustAmount())
                .build();

        /*RechargingMoneyTask rechargingMoneyTask = new RechargingMoneyTask(
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
        //3. wait
        //3-1. task-consumer
        //등록된 sub-task, status 모두 ok -> task 결과를 produce
        //4. task result consume
        //5. consume ok, Logic
        return null;
    }

    @Override
    public PayWalletMoney decreaseMoneyAmountRequest(DecreaseMoneyAmountCommand command) {
        //감액(사용)
        //1. TODO: 멤버십 회원 검증
        //2. money 금액 확인 (기존 금액 > 요청 금액)
        //2-1. 기존 금액 합산 쿼리(합산액은 0이하가 될 수 없다)
        Integer moneyTotalAmount = decreaseMoneyAmountPort.moneyTotalAmount(command.getBankAccountNumber());
        //2-2. 계산
        MoneyChangingResultStatus moneyChangingApprovalStatus
                = moneyAmountCalculator.checkAmountApproval(moneyTotalAmount, command.getRequestAdjustAmount());
        log.info("moneyTotalAmount:{}", moneyTotalAmount);
        //3. money 요청 히스토리 저장
        return decreaseMoneyAmountPort.decreaseMoneyAmount(command.toPayWalletMoney(moneyChangingApprovalStatus));
    }
}

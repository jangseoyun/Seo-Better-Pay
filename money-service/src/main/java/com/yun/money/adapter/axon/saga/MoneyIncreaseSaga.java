package com.yun.money.adapter.axon.saga;

import com.yun.money.adapter.axon.command.CheckRegisteredBankAccountCommand;
import com.yun.money.adapter.axon.command.RequestFirmBankingCommand;
import com.yun.money.adapter.axon.command.RollbackFirmBankingRequestCommand;
import com.yun.money.adapter.axon.event.CheckedRegisterBankAccountEvent;
import com.yun.money.adapter.axon.event.IncreaseRequestCreatedEvent;
import com.yun.money.adapter.axon.event.RequestFirmBankingResultEvent;
import com.yun.money.adapter.axon.event.RollbackFirmBankingResultEvent;
import com.yun.money.adapter.in.web.model.MoneyAdjustingResultStatus;
import com.yun.money.application.port.out.IncreaseMoneyAmountPort;
import com.yun.money.domain.PayWalletMoney;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Saga
public class MoneyIncreaseSaga {

    @Transient
    private final CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "increaseRequestId")
    public void handle(IncreaseRequestCreatedEvent event) {
        log.info("IncreaseRequestCreatedEvent start saga: {}", event);

        String checkRegisteredBankAccountId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("checkRegisteredBankAccountId", checkRegisteredBankAccountId);

        //충전 요청이 시작 되었다

        //뱅킹의 계좌 등록 여부 확인하기
        //check bank account
        //axon server를 통해서 banking service
        //CheckRegisteredBankAccountCommand banking service에서 받아줘야한다
        //기본적으로 axon framework에서 모든 aggregate의 변경은 aggregate 단위로 되어야만 한다.
        CheckRegisteredBankAccountCommand checkRegisteredBankAccountCommand = new CheckRegisteredBankAccountCommand(
                event.getRegisteredBankAccountAggregateIdentifier(),
                event.getIncreaseRequestId(),
                event.getMembershipId(),
                checkRegisteredBankAccountId,
                event.getBankName(),
                event.getBankAccountNumber(),
                event.getAmount());

        commandGateway.send(checkRegisteredBankAccountCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("throwable: {}", throwable);
            }

            log.info("result: {}", result.toString());

        });
    }

    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    public void handle(CheckedRegisterBankAccountEvent event) {
        log.info("MoneyIncreaseSaga CheckedRegisterBankAccountEvent: {}", event);
        boolean checked = event.isChecked();

        if (!checked) {
            log.error("event failed");
        }
        log.info("event success");

        String requestFirmBankingId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("requestFirmBankingId", requestFirmBankingId);

        //송금 요청
        //고객 계좌 -> 법인 계좌
        RequestFirmBankingCommand requestFirmBankingCommand = new RequestFirmBankingCommand(
                event.getFirmBankingRequestAggregateIdentifier(),
                requestFirmBankingId,
                event.getIncreaseRequestId(),
                event.getMembershipId(),
                event.getFromBankName(),
                event.getFromBankAccountNumber(),
                "seoBetterPay",
                "123-234-345",
                event.getAmount());
        commandGateway.send(requestFirmBankingCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("throwable: {}", throwable);
            }

            log.info("result: {}", result.toString());
        });
    }

    @SagaEventHandler(associationProperty = "requestFirmBankingId")
    public void handle(RequestFirmBankingResultEvent event, IncreaseMoneyAmountPort increaseMoneyAmountPort) {
        log.info("RequestFirmBankingResultEvent saga: {}", event);
        //실제로 DB update하는 단계
        //여기서 만약 fail이 나는 경우 롤백 이벤트를 만들어 줘야 한다.
        PayWalletMoney payWalletMoney = PayWalletMoney.generatedPayWalletDecreaseMoney(
                new PayWalletMoney.MoneyAdjustRequestId(event.getIncreaseRequestId()),
                new PayWalletMoney.MembershipId(event.getMembershipId()),
                new PayWalletMoney.MemberName(event.getMembershipId()),
                null,
                new PayWalletMoney.LinkedStatusIsValid(true),
                new PayWalletMoney.AdjustAmount(event.getMoneyAmount()),
                MoneyAdjustingResultStatus.SUCCEEDED
        );

        PayWalletMoney payWalletMoneyResult = null;
                //increaseMoneyAmountPort.increaseMoneyAmount(payWalletMoney);
        if (!payWalletMoneyResult.isLinkedStatusIsValid() || payWalletMoneyResult == null) {
            //실패시 롤백 이벤트
            String rollbackFirmBankingId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith("rollbackFirmBankingId", rollbackFirmBankingId);

            RollbackFirmBankingRequestCommand rollbackFirmBankingRequestCommand = new RollbackFirmBankingRequestCommand(
                    event.getRequestFirmBankingId(),
                    rollbackFirmBankingId,
                    event.getIncreaseRequestId(),
                    event.getMembershipId(),
                    event.getToBankName(),
                    event.getToBankAccountNumber(),
                    event.getMoneyAmount()
            );
            commandGateway.send(rollbackFirmBankingRequestCommand).whenComplete((result, throwable) -> {
                if (throwable != null) {
                    log.error("throwable: {}", throwable);
                }

                log.info("result: {}", result.toString());
                SagaLifecycle.end();
            });
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "rollbackFirmBankingId")
    public void handle(RollbackFirmBankingResultEvent event) {
        log.info("RollbackFirmBankingResultEvent: {}", event);
    }
}

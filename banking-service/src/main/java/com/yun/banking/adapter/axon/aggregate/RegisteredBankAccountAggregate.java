/*
package com.yun.banking.adapter.axon.aggregate;

import com.yun.banking.adapter.axon.command.CheckRegisteredBankAccountCommand;
import com.yun.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.yun.banking.adapter.axon.event.CheckedRegisterBankAccountEvent;
import com.yun.banking.adapter.axon.event.CreateRegisteredBankAccountEvent;
import com.yun.banking.adapter.out.external.bank.model.BankAccount;
import com.yun.banking.adapter.out.external.bank.model.CallApiBankAccountRequest;
import com.yun.banking.application.port.out.RequestBankAccountInfoPort;
import com.yun.banking.exception.BankingModuleException;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static com.yun.banking.exception.BankingServiceErrorCode.ENTITY_NOT_FOUND;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Aggregate
public class RegisteredBankAccountAggregate {

    @AggregateIdentifier
    private String id;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    @CommandHandler
    public RegisteredBankAccountAggregate(CreateRegisteredBankAccountCommand command) {
        log.info("RegisteredBankAccountAggregate CreateRegisteredBankAccountCommand: {}", command);
        apply(new CreateRegisteredBankAccountEvent(command.getMembershipId(), command.getBankName(), command.getBankAccountNumber()));
    }

    @CommandHandler
    public void handle(@NotNull CheckRegisteredBankAccountCommand command, RequestBankAccountInfoPort requestBankAccountInfoPort) {
        log.info("RegisteredBankAccountAggregate CheckRegisteredBankAccountCommand: {}", command);

        //command를 통해 이 어그리거트(RegisteredBankAccount)가 정상인지를 확인해야한다
        id = command.getAggregateIdentifier();

        //check registered bank account
        CallApiBankAccountRequest callApiBankAccountRequest = new CallApiBankAccountRequest(command.getBankName(), command.getBankAccountNumber());
        BankAccount bankAccount = requestBankAccountInfoPort.getBankAccountInfo(callApiBankAccountRequest).orElseThrow(() -> {
            throw new BankingModuleException(ENTITY_NOT_FOUND, ENTITY_NOT_FOUND.getMessage());
        });

        String firmBankingUUID = UUID.randomUUID().toString();

        //CheckedRegisterBankAccountEvent
        CheckedRegisterBankAccountEvent checkedRegisterBankAccountEvent = new CheckedRegisterBankAccountEvent(
                command.getIncreaseRequestId(),
                command.getCheckRegisteredBankAccountId(),
                command.getMembershipId(),
                bankAccount.isValid(),
                command.getAmount(),
                command.getAggregateIdentifier(),
                bankAccount.bankName(), //고객 계좌 -> 법인 계좌
                bankAccount.bankAccountNumber());

        apply(checkedRegisterBankAccountEvent);
    }

    @EventSourcingHandler
    public void on(CreateRegisteredBankAccountEvent event) {
        log.info("RegisteredBankAccountAggregate CreateRegisteredBankAccountEvent: {}", event);

        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        bankName = event.getBankName();
        bankAccountNumber = event.getBankAccountNumber();
    }
}
*/

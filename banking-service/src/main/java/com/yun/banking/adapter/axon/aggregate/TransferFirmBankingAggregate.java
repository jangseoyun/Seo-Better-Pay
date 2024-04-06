package com.yun.banking.adapter.axon.aggregate;

import com.yun.banking.adapter.axon.command.CreateFirmBankingCommand;
import com.yun.banking.adapter.axon.command.ProcessingFirmBankingCommand;
import com.yun.banking.adapter.axon.event.FirmBankingCreatedEvent;
import com.yun.banking.adapter.axon.event.ProcessingFirmBankingEvent;
import com.yun.banking.domain.TransferRequestStatusEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Aggregate
public class TransferFirmBankingAggregate {

    @AggregateIdentifier
    private String id;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int transferAmount;
    private TransferRequestStatusEnum transferRequestStatus;

    @CommandHandler
    public TransferFirmBankingAggregate(CreateFirmBankingCommand command) {
        log.info("TransferFirmBankingAggregate commandHandle: {}", command);
        apply(new FirmBankingCreatedEvent(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getTransferAmount()));
    }

    @CommandHandler
    public String handle(ProcessingFirmBankingCommand command) {
        log.info("TransferFirmBankingAggregate ProcessingTransferFirmBankingCommand: {}", command);

        id = command.getAggregateIdentifier();
        apply(new ProcessingFirmBankingEvent(command.getTransferRequestStatus()));

        return id;
    }

    @EventSourcingHandler
    public void on(FirmBankingCreatedEvent event) {
        log.info("TransferFirmBankingAggregate FirmBankingCreatedEvent: {}", event);

        id = UUID.randomUUID().toString();
        fromBankName = event.getFromBankName();
        fromBankAccountNumber = event.getFromBankAccountNumber();
        toBankName = event.getToBankName();
        toBankAccountNumber = event.getFromBankAccountNumber();
        transferAmount = event.getTransferAmount();
    }

    @EventSourcingHandler
    public void on(ProcessingFirmBankingEvent event) {
        log.info("TransferFirmBankingAggregate ProcessingFirmBankingEvent: {}", event);
        transferRequestStatus = event.getTransferRequestStatus();
    }
}

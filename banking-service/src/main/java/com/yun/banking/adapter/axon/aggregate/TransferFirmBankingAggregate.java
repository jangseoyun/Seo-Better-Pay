package com.yun.banking.adapter.axon.aggregate;

import com.yun.banking.adapter.axon.command.CreateFirmBankingCommand;
import com.yun.banking.adapter.axon.command.ProcessingFirmBankingCommand;
import com.yun.banking.adapter.axon.command.RequestFirmBankingCommand;
import com.yun.banking.adapter.axon.command.RollbackFirmBankingRequestCommand;
import com.yun.banking.adapter.axon.event.FirmBankingCreatedEvent;
import com.yun.banking.adapter.axon.event.ProcessingFirmBankingEvent;
import com.yun.banking.adapter.axon.event.RequestFirmBankingResultEvent;
import com.yun.banking.adapter.axon.event.RollbackFirmBankingResultEvent;
import com.yun.banking.adapter.out.external.bank.model.CallExternalTransferRequest;
import com.yun.banking.adapter.out.external.bank.model.FirmBankingResult;
import com.yun.banking.application.port.out.TransferExternalFirmBankingPort;
import com.yun.banking.application.port.out.TransferFirmBankingPort;
import com.yun.banking.domain.TransferFirmBanking;
import com.yun.banking.domain.TransferRequestStatusEnum;
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
    public TransferFirmBankingAggregate(RequestFirmBankingCommand command, TransferFirmBankingPort transferFirmBankingPort, TransferExternalFirmBankingPort transferExternalFirmBankingPort) {
        log.info("TransferFirmBankingAggregate: {}", command);
        id = command.getAggregateIdentifier();

        //firm banking 수행
        //개인 계좌 -> 법인 계좌 firm banking
        TransferFirmBanking transferFirmBanking = TransferFirmBanking.generateTransferFirmBaking(
                new TransferFirmBanking.TransferFirmBankingId(command.getRequestFirmBankingId()),
                new TransferFirmBanking.FromBankName(command.getToBankName()),
                new TransferFirmBanking.FromBankAccountNumber(command.getToBankAccountNumber()),
                new TransferFirmBanking.ToBankName("seoBetterPay"),
                new TransferFirmBanking.ToBankAccountNumber("123-234-345"),
                new TransferFirmBanking.TransferAmount(command.getMoneyAmount()),
                new TransferFirmBanking.TransferRequestStatus(TransferRequestStatusEnum.SUCCESS),
                new TransferFirmBanking.TransferAggregateIdentifier(id)
        );

        transferFirmBankingPort.createdTransferBankingInfo(transferFirmBanking);

        //firm banking
        CallExternalTransferRequest callExternalTransferRequest = new CallExternalTransferRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount(),
                command.getAggregateIdentifier()
        );

        FirmBankingResult firmBankingResult = transferExternalFirmBankingPort.transferFunds(callExternalTransferRequest);

        //성공 or 실패
        //firm banking 수행에 대한 결과 event
        RequestFirmBankingResultEvent requestFirmBankingResultEvent = new RequestFirmBankingResultEvent(
                command.getRequestFirmBankingId(),
                command.getIncreaseRequestId(),
                command.getMembershipId(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount(),
                firmBankingResult.transferRequestStatus().toString(),
                id
        );

        apply(requestFirmBankingResultEvent);
    }

    @CommandHandler
    public TransferFirmBankingAggregate(@NotNull RollbackFirmBankingRequestCommand command, TransferFirmBankingPort transferFirmBankingPort, TransferExternalFirmBankingPort transferExternalFirmBankingPort) {
        log.info("TransferFirmBankingAggregate RollbackFirmBankingRequestCommand:{}", command);
        id = UUID.randomUUID().toString();

        //계인 계좌 -> 법인 계좌
        //법인 계좌 -> 개인 계좌로 롤백 수행
        TransferFirmBanking transferFirmBanking = TransferFirmBanking.generateTransferFirmBaking(
                new TransferFirmBanking.TransferFirmBankingId(command.getAggregateIdentifier()),
                new TransferFirmBanking.FromBankName("seoBetterPay"),
                new TransferFirmBanking.FromBankAccountNumber("123-234-345"),
                new TransferFirmBanking.ToBankName(command.getBankName()),
                new TransferFirmBanking.ToBankAccountNumber(command.getBankAccountNumber()),
                new TransferFirmBanking.TransferAmount(command.getMoneyAmount()),
                new TransferFirmBanking.TransferRequestStatus(TransferRequestStatusEnum.ROLLBACK),
                new TransferFirmBanking.TransferAggregateIdentifier(id)
        );

        TransferFirmBanking transferFirmBankingResult = transferFirmBankingPort.createdTransferBankingInfo(transferFirmBanking);

        RequestFirmBankingResultEvent requestFirmBankingResultEvent = new RequestFirmBankingResultEvent(
                command.getAggregateIdentifier(),
                command.getIncreaseRequestId(),
                command.getMembershipId(),
                command.getBankName(),
                command.getBankAccountNumber(),
                command.getMoneyAmount(),
                transferFirmBankingResult.getTransferRequestStatus().name(),
                id
        );

        //firm banking
        CallExternalTransferRequest callExternalTransferRequest = new CallExternalTransferRequest(
                "seoBetterPay",
                "123-234-345",
                command.getBankName(),
                command.getBankAccountNumber(),
                command.getMoneyAmount(),
                command.getAggregateIdentifier()
        );

        FirmBankingResult firmBankingResult = transferExternalFirmBankingPort.transferFunds(callExternalTransferRequest);


        RollbackFirmBankingResultEvent rollbackFirmBankingResultEvent = new RollbackFirmBankingResultEvent(
                command.getRollbackFirmBankingId(),
                command.getMembershipId(),
                id
        );

        apply(rollbackFirmBankingResultEvent);
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
    public void on(ProcessingFirmBankingEvent event, TransferFirmBankingPort transferFirmBankingPort) {
        log.info("TransferFirmBankingAggregate ProcessingFirmBankingEvent: {}", event);
        transferRequestStatus = event.getTransferRequestStatus();
    }
}

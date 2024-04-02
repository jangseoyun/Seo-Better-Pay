package com.yun.banking.application.service;

import com.yun.banking.adapter.axon.command.CreateFirmBankingCommand;
import com.yun.banking.adapter.out.external.bank.model.CallExternalTransferRequest;
import com.yun.banking.adapter.out.external.bank.model.FirmBankingResult;
import com.yun.banking.application.port.in.TransferFirmBankingCommand;
import com.yun.banking.application.port.in.TransferFirmBankingUseCase;
import com.yun.banking.application.port.out.TransferExternalFirmBankingPort;
import com.yun.banking.application.port.out.TransferFirmBankingPort;
import com.yun.banking.domain.TransferFirmBanking;
import com.yun.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class TransferFirmBankingService implements TransferFirmBankingUseCase {

    private final TransferFirmBankingPort transferFirmBankingPort;
    private final TransferExternalFirmBankingPort transferExternalFirmBankingPort;
    private final CommandGateway commandGateway;

    @Override
    public TransferFirmBanking sendTransferRequest(TransferFirmBankingCommand command) {
        //송금 요청
        //1. a -> b 계좌
        //2. 요청( repository 요청 상태로 저장 )
        TransferFirmBanking transferFirmBanking = transferFirmBankingPort.createdTransferBankingInfo(command.toRequestTransferFirmBanking());
        //3. TODO: 외부 은행에 펌뱅킹 요청
        FirmBankingResult firmBankingResult
                = transferExternalFirmBankingPort.transferFunds(command.toExternalRequest());
        //4. TODO: 3번 결과에 따라서 2번에서 작성했던 request 정보를 update 한다
       /* if (firmBankingResult.transferRequestStatus().equals(TransferRequestStatusEnum.SUCCESS)) {
            //성공으로 update
        }*/
        return transferFirmBanking;
    }

    @Override
    public void sendTransferRequestByEvent(TransferFirmBankingCommand command) {
        //command -> event sourcing
        CreateFirmBankingCommand axonCommand = CreateFirmBankingCommand.builder()
                .fromBankName(command.getFromBankName())
                .fromBankAccountNumber(command.getFromBankAccountNumber())
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .transferAmount(command.getTransferAmount())
                .build();

        CallExternalTransferRequest callExternalTransferRequest = new CallExternalTransferRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                UUID.randomUUID().toString());

        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                log.info("throwable:{}", throwable);
            }

            transferExternalFirmBankingPort.transferFunds(callExternalTransferRequest);
            //firmbanking의 DB에 save
        });
    }
}

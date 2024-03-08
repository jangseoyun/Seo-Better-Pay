package com.yun.banking.application.service;

import com.yun.banking.adapter.out.external.bank.model.FirmBankingResult;
import com.yun.banking.application.port.in.TransferFirmBankingCommand;
import com.yun.banking.application.port.in.TransferFirmBankingUseCase;
import com.yun.banking.application.port.out.TransferExternalFirmBankingPort;
import com.yun.banking.application.port.out.TransferFirmBankingPort;
import com.yun.banking.domain.TransferFirmBanking;
import com.yun.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class TransferFirmBankingService implements TransferFirmBankingUseCase {

    private final TransferFirmBankingPort transferFirmBankingPort;
    private final TransferExternalFirmBankingPort transferExternalFirmBankingPort;

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
}

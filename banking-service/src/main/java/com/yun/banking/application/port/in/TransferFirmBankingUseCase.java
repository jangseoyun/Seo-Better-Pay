package com.yun.banking.application.port.in;

import com.yun.banking.domain.TransferFirmBanking;

public interface TransferFirmBankingUseCase {
    TransferFirmBanking sendTransferRequest(TransferFirmBankingCommand command);
}

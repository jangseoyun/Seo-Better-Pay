package com.yun.banking.application.port.out;

import com.yun.banking.domain.TransferFirmBanking;

public interface TransferFirmBankingPort {
    TransferFirmBanking createdTransferBankingInfo(TransferFirmBanking transferFirmBanking);
    void processingTransferFirmBanking(TransferFirmBanking.TransferAggregateIdentifier firmBankingRequestAggregateIdentifier);
}

package com.yun.banking.adapter.out.external.bank.model;

import com.yun.banking.domain.TransferRequestStatusEnum;

public record FirmBankingResult(
        TransferRequestStatusEnum transferRequestStatus

) {
    public FirmBankingResult(TransferRequestStatusEnum transferRequestStatus) {
        this.transferRequestStatus = transferRequestStatus;
    }
}

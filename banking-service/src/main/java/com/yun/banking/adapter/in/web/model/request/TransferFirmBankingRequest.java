package com.yun.banking.adapter.in.web.model.request;

import com.yun.banking.application.port.in.TransferFirmBankingCommand;

public record TransferFirmBankingRequest(
        String fromBankName,
        String fromBankAccountNumber,
        String toBankName,
        String toBankAccountNumber,
        int transferAmount //won
) {
    public TransferFirmBankingCommand toCommand() {
        return TransferFirmBankingCommand.of(
                this.fromBankName,
                this.fromBankAccountNumber,
                this.toBankName,
                this.toBankAccountNumber,
                this.transferAmount
        );
    }
}

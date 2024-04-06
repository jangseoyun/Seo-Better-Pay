package com.yun.banking.adapter.in.web.model.request;

import com.yun.banking.application.port.in.ProcessingTransferFirmBankingCommand;

public record ProcessingTransferFirmBankingRequest(
        String firmBankingRequestAggregateIdentifier
) {
    public ProcessingTransferFirmBankingCommand toCommand() {
        return ProcessingTransferFirmBankingCommand.of(firmBankingRequestAggregateIdentifier);
    }
}

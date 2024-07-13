package com.yun.banking.application.port.in;

import com.yun.banking.adapter.in.web.model.request.ProcessingTransferFirmBankingRequest;
import com.yun.banking.domain.TransferRequestStatusEnum;
import com.yun.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ProcessingTransferFirmBankingCommand extends SelfValidating<ProcessingTransferFirmBankingRequest> {
    @NotNull
    @NotEmpty
    private String firmBankingRequestAggregateIdentifier;
    private TransferRequestStatusEnum transferRequestStatus;

    private ProcessingTransferFirmBankingCommand(String firmBankingRequestAggregateIdentifier) {
        this.firmBankingRequestAggregateIdentifier = firmBankingRequestAggregateIdentifier;
        this.transferRequestStatus = TransferRequestStatusEnum.PENDING;
        this.validateSelf();
    }

    public static ProcessingTransferFirmBankingCommand of(String firmBankingRequestAggregateIdentifier) {
        return new ProcessingTransferFirmBankingCommand(firmBankingRequestAggregateIdentifier);
    }
}

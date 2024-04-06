package com.yun.banking.adapter.axon.command;

import com.yun.banking.domain.TransferRequestStatusEnum;
import com.yun.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class ProcessingFirmBankingCommand extends SelfValidating<ProcessingFirmBankingCommand> {
    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private TransferRequestStatusEnum transferRequestStatus;
}

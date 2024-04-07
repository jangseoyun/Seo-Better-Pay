package com.yun.banking.adapter.axon.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RollbackFirmBankingRequestCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rollbackFirmBankingId;
    private String increaseRequestId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private int moneyAmount;
}

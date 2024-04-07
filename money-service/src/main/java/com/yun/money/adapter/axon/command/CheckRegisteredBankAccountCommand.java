package com.yun.money.adapter.axon.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CheckRegisteredBankAccountCommand {

    @TargetAggregateIdentifier
    private String aggregateIdentifier; //registeredBankAccount
    private String increaseRequestId;
    private String membershipId;
    private String checkRegisteredBankAccountId;
    private String bankName;
    private String bankAccountNumber;
    private int amount;
}

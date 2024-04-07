package com.yun.money.adapter.axon.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddMoneyRequestCreateCommand {

    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String increaseRequestId;
    private String membershipId;
    private int amount;
}

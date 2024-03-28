package com.yun.money.adapter.axon.command;

import com.yun.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class IncreaseMemberMoneyCommand extends SelfValidating<MemberMoneyCreateCommand> {

    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    @NotNull
    @NotEmpty
    private String membershipId;

    @NotNull
    private Integer increaseAmount;

    public IncreaseMemberMoneyCommand(String aggregateIdentifier, String membershipId, Integer increaseAmount) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.membershipId = membershipId;
        this.increaseAmount = increaseAmount;
        this.validateSelf();
    }
}

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
public class MemberMoneyCreateCommand extends SelfValidating<MemberMoneyCreateCommand> {
    @NotNull
    @NotEmpty
    @TargetAggregateIdentifier
    private String membershipId;

    public MemberMoneyCreateCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}

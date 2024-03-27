package com.yun.money.adapter.axon.command;

import com.yun.common.SelfValidating;
import com.yun.money.application.port.in.MembershipIdCommand;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyAddCommand extends SelfValidating<MembershipIdCommand> {
    @NotNull
    @NotEmpty
    private String membershipId;

    public MemberMoneyAddCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}

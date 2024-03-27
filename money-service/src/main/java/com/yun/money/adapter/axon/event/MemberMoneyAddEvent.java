package com.yun.money.adapter.axon.event;

import com.yun.common.SelfValidating;
import com.yun.money.adapter.axon.command.MemberMoneyAddCommand;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyAddEvent extends SelfValidating<MemberMoneyAddCommand> {
    @NotNull
    @NotEmpty
    private final String membershipId;

    public MemberMoneyAddEvent(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}

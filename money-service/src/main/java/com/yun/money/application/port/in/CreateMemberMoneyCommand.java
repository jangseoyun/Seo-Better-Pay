package com.yun.money.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.money.adapter.in.web.model.MembershipIdRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CreateMemberMoneyCommand extends SelfValidating<MembershipIdRequest> {

    @NotNull
    @NotEmpty
    private final String membershipId;

    public CreateMemberMoneyCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}

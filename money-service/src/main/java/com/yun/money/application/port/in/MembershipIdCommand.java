package com.yun.money.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.money.adapter.in.web.model.MembershipIdRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class MembershipIdCommand extends SelfValidating<MembershipIdRequest> {

    @NotNull
    @NotEmpty
    private final String membershipId;

    public MembershipIdCommand(String membershipId) {
        this.membershipId = membershipId;
    }
}

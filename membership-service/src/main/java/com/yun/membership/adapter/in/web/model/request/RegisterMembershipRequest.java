package com.yun.membership.adapter.in.web.model.request;

import com.yun.membership.application.port.in.RegisterMembershipCommand;

public record RegisterMembershipRequest(
        String membershipId,
        String membershipPw,
        String membershipEmail,
        String name,
        String address
) {
    public RegisterMembershipCommand toCommand() {
        return RegisterMembershipCommand.of(
                this.membershipId,
                this.membershipPw,
                this.membershipEmail,
                this.name,
                this.address,
                false
        );
    }
}

package com.yun.membership.adapter.in.web.model.request;

import com.yun.membership.application.port.in.RegisterMembershipCommand;

public record RegisterMembershipRequest(
        String name,
        String address,
        String email,
        boolean isCorg
) {
    public RegisterMembershipCommand toCommand() {
        return RegisterMembershipCommand.of(
                this.name,
                this.email,
                this.address,
                true,
                this.isCorg
        );
    }
}

package com.yun.membership.adapter.in.web.model.request;

import com.yun.membership.application.port.in.ModifyMembershipCommand;

public record ModifyMembershipRequest(
        String membershipId,
        String name,
        String email,
        String address
) {
    public ModifyMembershipCommand toCommand() {
        return ModifyMembershipCommand.of(
                membershipId,
                name,
                email,
                address
        );
    }
}

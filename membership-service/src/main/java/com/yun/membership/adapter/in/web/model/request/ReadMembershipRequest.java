package com.yun.membership.adapter.in.web.model.request;

import com.yun.membership.application.port.in.ReadMembershipCommand;

public record ReadMembershipRequest(
   String membershipId
) {
    public ReadMembershipCommand toCommand() {
        return ReadMembershipCommand.of(membershipId);
    }
}

package com.yun.money.adapter.in.web.model;

import com.yun.money.application.port.in.MembershipIdCommand;

public record MembershipIdRequest(
        String membershipId
) {
    public MembershipIdCommand toCommand() {
        return new MembershipIdCommand(membershipId);
    }
}

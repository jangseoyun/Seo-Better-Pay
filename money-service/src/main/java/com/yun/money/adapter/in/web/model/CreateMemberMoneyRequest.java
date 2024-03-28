package com.yun.money.adapter.in.web.model;

import com.yun.money.application.port.in.CreateMemberMoneyCommand;

public record CreateMemberMoneyRequest(
        String membershipId
) {
    public CreateMemberMoneyCommand toCommand() {
        return new CreateMemberMoneyCommand(membershipId);
    }
}

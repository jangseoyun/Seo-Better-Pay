package com.yun.banking.adapter.in.web.model.request;

import com.yun.banking.application.port.in.ReadBankAccountCommand;

public record SearchBankAccountRequest(
        String membershipId
) {

    public ReadBankAccountCommand toCommand() {
        return ReadBankAccountCommand.of(membershipId);
    }
}

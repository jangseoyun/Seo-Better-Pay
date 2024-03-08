package com.yun.banking.adapter.in.web.model.request;

import com.yun.banking.application.port.in.RegisterBankAccountCommand;

public record RegisterBankAccountRequest(
        String membershipId,
        String bankName,
        String bankAccountNumber,
        boolean linkedStatusIsValid
) {
    public static RegisterBankAccountRequest of(String membershipId,
                                                String bankName,
                                                String bankAccountNumber) {
        return new RegisterBankAccountRequest(membershipId,
                bankName,
                bankAccountNumber,
                false);
    }

    public RegisterBankAccountCommand toCommand() {
        return RegisterBankAccountCommand.of(
               this.membershipId,
               this.bankName,
               this.bankAccountNumber,
               this.linkedStatusIsValid
        );
    }

}

package com.yun.money.application.port.out.banking;

public record LinkedBankAccountInfo(
        String registeredBankAccountId,
        String membershipId,
        String bankName,
        String bankAccountNumber,
        boolean linkedStatusIsValid
) {
}

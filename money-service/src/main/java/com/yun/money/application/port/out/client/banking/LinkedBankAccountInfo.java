package com.yun.money.application.port.out.client.banking;

public record LinkedBankAccountInfo(
        String registeredBankAccountId,
        String membershipId,
        String bankName,
        String bankAccountNumber,
        boolean linkedStatusIsValid
) {
}

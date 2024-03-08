package com.yun.banking.adapter.out.external.bank.model;

public record CallApiBankAccountRequest(
        String bankName,
        String bankAccountNumber
) {
    public static CallApiBankAccountRequest of(String bankName, String bankAccountNumber) {
        return new CallApiBankAccountRequest(bankName, bankAccountNumber);
    }
}

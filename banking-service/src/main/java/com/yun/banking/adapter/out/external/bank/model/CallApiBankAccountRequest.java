package com.yun.banking.adapter.out.external.bank.model;

public record CallApiBankAccountRequest(
        String bankCode,
        String bankName,
        String bankAccountNumber,
        boolean isValid
) {
    public static CallApiBankAccountRequest of(String bankCode, String bankName, String bankAccountNumber, boolean isValid) {
        return new CallApiBankAccountRequest(bankCode, bankName, bankAccountNumber, isValid);
    }
}

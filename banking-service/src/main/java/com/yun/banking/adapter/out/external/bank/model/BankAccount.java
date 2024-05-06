package com.yun.banking.adapter.out.external.bank.model;

public record BankAccount(
        String bankCode,
        String bankName,
        String bankAccountNumber,
        boolean isValid
) {
}

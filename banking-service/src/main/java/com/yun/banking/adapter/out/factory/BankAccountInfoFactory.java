package com.yun.banking.adapter.out.factory;

import com.yun.banking.adapter.out.external.bank.model.BankAccount;
import com.yun.banking.adapter.out.external.bank.model.CallApiBankAccountRequest;

public class BankAccountInfoFactory {
    public static BankAccount newBankAccount(CallApiBankAccountRequest request) {
        return new BankAccount(
                request.bankCode(),
                request.bankName(),
                request.bankAccountNumber(),
                request.isValid());
    }
}

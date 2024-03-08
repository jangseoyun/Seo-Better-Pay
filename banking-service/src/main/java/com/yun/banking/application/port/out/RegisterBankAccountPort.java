package com.yun.banking.application.port.out;

import com.yun.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {
    RegisteredBankAccount createdBankAccount(RegisteredBankAccount registeredBankAccount);
}

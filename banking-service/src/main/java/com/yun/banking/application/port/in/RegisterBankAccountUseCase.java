package com.yun.banking.application.port.in;

import com.yun.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccountByMembership(RegisterBankAccountCommand command);
    void registerBankAccountByMembershipByEvent(RegisterBankAccountCommand command);
}

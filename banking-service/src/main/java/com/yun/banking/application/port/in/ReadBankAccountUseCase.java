package com.yun.banking.application.port.in;

import com.yun.banking.domain.RegisteredBankAccount;

import java.util.List;

public interface ReadBankAccountUseCase {
    List<RegisteredBankAccount> searchLinkedBankAccounts(ReadBankAccountCommand command);
}

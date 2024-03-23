package com.yun.banking.application.port.out;

import com.yun.banking.domain.RegisteredBankAccount;

import java.util.List;

public interface ReadBankAccountPort {
    List<RegisteredBankAccount> getLinkedBankAccounts(String membershipId);
}

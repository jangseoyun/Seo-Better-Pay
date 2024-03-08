package com.yun.banking.adapter.out.persistence;

import com.yun.banking.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {
    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountEntity registeredBankAccount) {
        return RegisteredBankAccount.generateBankAccount(
                new RegisteredBankAccount.BankAccountId(registeredBankAccount.getId()+""),
                new RegisteredBankAccount.MembershipId(registeredBankAccount.getMembershipId()),
                new RegisteredBankAccount.BankName(registeredBankAccount.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(registeredBankAccount.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(registeredBankAccount.isLinkedStatusIsValid())
        );
    }
}

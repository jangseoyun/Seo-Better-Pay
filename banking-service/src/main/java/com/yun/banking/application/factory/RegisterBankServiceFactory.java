package com.yun.banking.application.factory;

import com.yun.banking.application.port.in.RegisterBankAccountCommand;
import com.yun.banking.domain.RegisteredBankAccount;

import java.util.UUID;

public class RegisterBankServiceFactory {
    public static RegisteredBankAccount newRegisterBankAccount(RegisterBankAccountCommand command) {
        return RegisteredBankAccount.generateBankAccount(
                new RegisteredBankAccount.BankAccountId(UUID.randomUUID().toString()),
                new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                new RegisteredBankAccount.BankCodeStd(command.getBankCodeStd()),
                new RegisteredBankAccount.RegisterAccountNum(command.getRegisterAccountNum()),
                new RegisteredBankAccount.UserSocialNum(command.getUserSocialNum()),
                new RegisteredBankAccount.UserName(command.getUserName()),
                new RegisteredBankAccount.UserCi(command.getUserCi()),
                new RegisteredBankAccount.Scope(command.getScopeState()),
                new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid()),
                new RegisteredBankAccount.RegisteredAggregateIdentifier("")
        );
    }
}

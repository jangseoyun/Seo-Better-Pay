package com.yun.banking.adapter.out.persistence;

import com.yun.banking.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {
    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountEntity registeredBankAccount) {
        return RegisteredBankAccount.generateBankAccount(
                new RegisteredBankAccount.BankAccountId(registeredBankAccount.getId()+""),
                new RegisteredBankAccount.MembershipId(registeredBankAccount.getMembershipId()),
                new RegisteredBankAccount.BankCodeStd(registeredBankAccount.getBankAccountCode()),
                new RegisteredBankAccount.RegisterAccountNum(registeredBankAccount.getBankAccountNumber()),
                new RegisteredBankAccount.UserSocialNum(registeredBankAccount.getUserSocialNumber()),
                new RegisteredBankAccount.UserName(registeredBankAccount.getUserName()),
                new RegisteredBankAccount.UserCi(registeredBankAccount.getUserCi()),
                new RegisteredBankAccount.Scope(registeredBankAccount.getScope()),
                new RegisteredBankAccount.LinkedStatusIsValid(registeredBankAccount.isLinkedStatusIsValid()),
                new RegisteredBankAccount.RegisteredAggregateIdentifier(registeredBankAccount.getAggregateIdentifier())
        );
    }

    public RegisteredBankAccountEntity toEntity(RegisteredBankAccount registeredBankAccount) {
        return RegisteredBankAccountEntity.builder()
                .id(null)
                .membershipId(registeredBankAccount.getMembershipId())
                .bankAccountCode(registeredBankAccount.getBankCodeStd())
                .bankAccountNumber(registeredBankAccount.getRegisterAccountNum())
                .userSocialNumber(registeredBankAccount.getUserSocialNum())
                .userName(registeredBankAccount.getUserName())
                .userCi(registeredBankAccount.getUserCi())
                .scope(registeredBankAccount.getScope())
                .linkedStatusIsValid(true)
                .aggregateIdentifier(registeredBankAccount.getAggregateIdentifier())
                .build();
    }
}

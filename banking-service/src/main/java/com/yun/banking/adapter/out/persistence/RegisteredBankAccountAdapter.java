package com.yun.banking.adapter.out.persistence;

import com.yun.banking.application.port.out.RegisterBankAccountPort;
import com.yun.banking.domain.RegisteredBankAccount;
import com.yun.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountAdapter implements RegisterBankAccountPort {

    private final RegisteredBankAccountJpaRepository bankAccountJpaRepository;
    private final RegisteredBankAccountMapper mapper;

    @Override
    public RegisteredBankAccount createdBankAccount(RegisteredBankAccount registeredBankAccount) {
        RegisteredBankAccountEntity bankAccountEntity = bankAccountJpaRepository.save(
                RegisteredBankAccountEntity.of(
                        registeredBankAccount.getMembershipId(),
                        registeredBankAccount.getBankName(),
                        registeredBankAccount.getBankAccountNumber(),
                        registeredBankAccount.isLinkedStatusIsValid(),
                        registeredBankAccount.getAggregateIdentifier())
        );
        return mapper.mapToDomainEntity(bankAccountEntity);
    }
}

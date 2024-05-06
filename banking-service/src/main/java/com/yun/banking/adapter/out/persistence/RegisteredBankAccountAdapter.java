package com.yun.banking.adapter.out.persistence;

import com.yun.banking.application.port.out.RegisterBankAccountPort;
import com.yun.banking.domain.RegisteredBankAccount;
import com.yun.common.anotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountAdapter implements RegisterBankAccountPort {

    private final RegisteredBankAccountJpaRepository bankAccountJpaRepository;
    private final RegisteredBankAccountMapper mapper;

    @Override
    public RegisteredBankAccount createdBankAccount(RegisteredBankAccount registeredBankAccount) {
        RegisteredBankAccountEntity bankAccountEntity = bankAccountJpaRepository.save(
                mapper.toEntity(registeredBankAccount)
        );

        return mapper.mapToDomainEntity(bankAccountEntity);
    }

}

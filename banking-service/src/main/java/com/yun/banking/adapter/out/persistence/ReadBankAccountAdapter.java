package com.yun.banking.adapter.out.persistence;

import com.yun.banking.application.port.out.ReadBankAccountPort;
import com.yun.banking.domain.RegisteredBankAccount;
import com.yun.common.anotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReadBankAccountAdapter implements ReadBankAccountPort {

    private final RegisteredBankAccountJpaRepository registeredBankAccountJpaRepository;
    private final RegisteredBankAccountMapper mapper;

    @Override
    public List<RegisteredBankAccount> getLinkedBankAccounts(String membershipId) {
        return registeredBankAccountJpaRepository.getLinkedBankAccount(membershipId)
                .stream()
                .map(account -> mapper.of(account))
                .collect(Collectors.toList());
    }
}

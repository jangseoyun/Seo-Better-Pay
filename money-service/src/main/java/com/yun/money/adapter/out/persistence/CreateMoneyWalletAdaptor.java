package com.yun.money.adapter.out.persistence;

import com.yun.common.anotation.PersistenceAdapter;
import com.yun.money.application.port.out.CreateMoneyWalletPort;
import com.yun.money.domain.MemberMoneyWallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class CreateMoneyWalletAdaptor implements CreateMoneyWalletPort {

    private final MemberMoneyWalletJpaRepository memberMoneyWalletJpaRepository;
    private final MemberMoneyWalletMapper mapper;

    @Override
    public MemberMoneyWallet processGetMemberMoneyWalletBalance(MemberMoneyWallet.MembershipId membershipId,
                                                                MemberMoneyWallet.MoneyAggregateIdentifier aggregateIdentifier) {
        MemberMoneyWalletEntity memberMoneyWalletEntity = MemberMoneyWalletEntity.create(membershipId.getMembershipId(), aggregateIdentifier.getMoneyAggregateIdentifier(), 0);
        MemberMoneyWalletEntity createdMemberMoneyWallet = memberMoneyWalletJpaRepository.save(memberMoneyWalletEntity);
        log.info("processGetMemberMoneyWalletBalance: {}", createdMemberMoneyWallet);
        return mapper.mapToMemberMoneyWallet(createdMemberMoneyWallet);
    }
}

package com.yun.money.adapter.out.persistence;

import com.yun.common.PersistenceAdapter;
import com.yun.money.application.port.in.MembershipIdCommand;
import com.yun.money.application.port.out.CalculateMoneyWalletPort;
import com.yun.money.domain.MemberMoneyWallet;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CalculateMoneyWalletAdaptor implements CalculateMoneyWalletPort {

    private final PayWalletMoneyJpaRepository payWalletMoneyJpaRepository;
    private final MemberMoneyWalletMapper mapper;

    @Override
    public MemberMoneyWallet processGetMemberMoneyWalletBalance(MembershipIdCommand command) {
        Integer moneyTotalAmount = payWalletMoneyJpaRepository.moneyTotalAmount(command.getMembershipId());
        return mapper.mapToMemberMoneyWallet(moneyTotalAmount, command.getMembershipId());
    }
}

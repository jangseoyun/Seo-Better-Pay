package com.yun.money.adapter.out.persistence;

import com.yun.money.domain.MemberMoneyWallet;
import org.springframework.stereotype.Component;

@Component
public class MemberMoneyWalletMapper {
    public MemberMoneyWallet mapToMemberMoneyWallet(MemberMoneyWalletEntity entity) {
        return MemberMoneyWallet.generateMemberMoneyWallet(
                new MemberMoneyWallet.MembershipId(entity.getMembershipId()),
                new MemberMoneyWallet.MoneyTotalAmount(entity.getMoneyTotalAmount()),
                new MemberMoneyWallet.MoneyAggregateIdentifier(entity.getMoneyAggregateIdentifier())
        );
    }

}

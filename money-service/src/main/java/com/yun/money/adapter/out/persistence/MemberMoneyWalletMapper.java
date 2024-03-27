package com.yun.money.adapter.out.persistence;

import com.yun.money.domain.MemberMoneyWallet;
import org.springframework.stereotype.Component;

@Component
public class MemberMoneyWalletMapper {
    public MemberMoneyWallet mapToMemberMoneyWallet(Integer moneyTotalBalance, String membershipId) {
        return MemberMoneyWallet.generateMemberMoneyWallet(
                new MemberMoneyWallet.MembershipId(membershipId),
                new MemberMoneyWallet.MoneyTotalAmount(moneyTotalBalance)
        );
    }
}

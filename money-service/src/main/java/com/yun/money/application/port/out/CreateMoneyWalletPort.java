package com.yun.money.application.port.out;

import com.yun.money.domain.MemberMoneyWallet;

public interface CreateMoneyWalletPort {
    MemberMoneyWallet processGetMemberMoneyWalletBalance(MemberMoneyWallet.MembershipId membershipId,
                                                         MemberMoneyWallet.MoneyAggregateIdentifier aggregateIdentifier);
}

package com.yun.money.application.port.out;

import com.yun.money.application.port.in.MembershipIdCommand;
import com.yun.money.domain.MemberMoneyWallet;

public interface CalculateMoneyWalletPort {
    MemberMoneyWallet processGetMemberMoneyWalletBalance(MembershipIdCommand command);
}

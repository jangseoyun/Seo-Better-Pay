package com.yun.money.application.port.in;

import com.yun.money.domain.MemberMoneyWallet;

public interface MoneyWalletCalculatorUseCase {
    MemberMoneyWallet MemberMoneyBalanceCalculate(MembershipIdCommand command);
}

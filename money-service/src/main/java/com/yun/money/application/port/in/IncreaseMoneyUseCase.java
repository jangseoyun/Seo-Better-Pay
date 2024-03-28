package com.yun.money.application.port.in;

import com.yun.money.domain.MemberMoneyWallet;
import com.yun.money.domain.PayWalletMoney;

public interface IncreaseMoneyUseCase {
    PayWalletMoney addMoneyToSeobetterpay(IncreaseMoneyAmountCommand command);
    PayWalletMoney addMoneyToSeobetterpayAsync(IncreaseMoneyAmountCommand command);
    void addMoneyToSeobetterpayByEvent(MemberMoneyWallet memberMoneyWallet, IncreaseMoneyAmountCommand command);
}

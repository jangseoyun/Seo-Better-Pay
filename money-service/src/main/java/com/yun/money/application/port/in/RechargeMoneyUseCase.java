package com.yun.money.application.port.in;

import com.yun.money.domain.MemberMoneyWallet;
import com.yun.money.domain.PayWalletMoney;

public interface RechargeMoneyUseCase {
    PayWalletMoney rechargingMoneyToSeobetterpay(RechargeMoneyAmountCommand command);
    PayWalletMoney rechargingMoneyToSeobetterpayAsync(RechargeMoneyAmountCommand command);
    void addMoneyToSeobetterpayByEvent(MemberMoneyWallet memberMoneyWallet, RechargeMoneyAmountCommand command);
}

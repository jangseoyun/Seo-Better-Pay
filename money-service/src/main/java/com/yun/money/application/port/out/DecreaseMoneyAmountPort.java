package com.yun.money.application.port.out;

import com.yun.money.domain.PayWalletMoney;

public interface DecreaseMoneyAmountPort {
    PayWalletMoney decreaseMoneyAmount(PayWalletMoney payWalletMoney);
}

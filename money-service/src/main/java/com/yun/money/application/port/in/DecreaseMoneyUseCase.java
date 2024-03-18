package com.yun.money.application.port.in;

import com.yun.money.domain.PayWalletMoney;

public interface DecreaseMoneyUseCase {
    PayWalletMoney payWithSeobetterpay(DecreaseMoneyAmountCommand command);
}

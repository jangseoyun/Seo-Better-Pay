package com.yun.money.application.port.in;

import com.yun.money.domain.PayWalletMoney;

public interface AdjustMoneyUseCase {
    PayWalletMoney increaseMoneyAmountRequest(IncreaseMoneyAmountCommand command);
    PayWalletMoney decreaseMoneyAmountRequest(DecreaseMoneyAmountCommand command);
}

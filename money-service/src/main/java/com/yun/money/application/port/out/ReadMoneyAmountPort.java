package com.yun.money.application.port.out;

import com.yun.money.domain.PayWalletMoney;

import java.util.List;

public interface ReadMoneyAmountPort {
    Integer moneyTotalAmount(String bankAccountNumber);
    List<PayWalletMoney> getAddMoneyHistory(String membershipId);
    List<PayWalletMoney> getPayMoneyHistory(String membershipId);
}

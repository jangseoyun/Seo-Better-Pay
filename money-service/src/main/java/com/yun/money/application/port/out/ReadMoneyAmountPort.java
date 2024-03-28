package com.yun.money.application.port.out;

import com.yun.money.application.port.in.IncreaseMoneyAmountCommand;
import com.yun.money.domain.MemberMoneyWallet;
import com.yun.money.domain.PayWalletMoney;

import java.util.List;

public interface ReadMoneyAmountPort {
    Integer moneyTotalAmount(String bankAccountNumber);
    List<PayWalletMoney> getAddMoneyHistory(String membershipId);
    List<PayWalletMoney> getPayMoneyHistory(String membershipId);
    MemberMoneyWallet getMemberMoneyWallet(String membershipId);
    void addMoneyToSeobetterpayByEvent(IncreaseMoneyAmountCommand command);
}

package com.yun.money.application.port.in;

import com.yun.money.domain.MemberMoneyWallet;
import com.yun.money.domain.PayWalletMoney;

import java.util.List;

public interface ReadMoneyAmountUseCase {
    Integer getMoneyTotalAmount(MembershipIdCommand command);
    List<PayWalletMoney> getAddMoneyHistory(MembershipIdCommand command);
    List<PayWalletMoney> getPayMoneyHistory(MembershipIdCommand command);
    MemberMoneyWallet getMemberMoneyWallet(MembershipIdCommand command);
}

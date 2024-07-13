package com.yun.money.adapter.out.persistence.factory;

import com.yun.money.adapter.in.web.model.MoneyAdjustingResultStatus;
import com.yun.money.adapter.out.persistence.PayWalletMoneyEntity;
import com.yun.money.domain.PayWalletMoney;

public class PayWalletMoneyFactory {
    public static PayWalletMoneyEntity toEntity(PayWalletMoney payWalletMoney) {
        return PayWalletMoneyEntity.builder()
                .moneyRequestId(payWalletMoney.getMoneyAdjustRequestId())
                .membershipId(payWalletMoney.getMembershipId())
                .membershipName(payWalletMoney.getMemberName())
                .linkedBankCode(payWalletMoney.getLinkedBankCode())
                .linkedBankAccountNumber(payWalletMoney.getLinkedBankAccountNumber())
                .adjustAmount(payWalletMoney.getAdjustAmount())
                .linkedStatusIsValid(payWalletMoney.isLinkedStatusIsValid())
                .moneyChangType(payWalletMoney.getMoneyAdjustingType())
                .moneyChangingResultStatus(MoneyAdjustingResultStatus.SUCCEEDED)
                .build();
    }
}

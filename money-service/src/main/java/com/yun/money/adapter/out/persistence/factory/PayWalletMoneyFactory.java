package com.yun.money.adapter.out.persistence.factory;

import com.yun.money.adapter.out.persistence.PayWalletMoneyEntity;
import com.yun.money.domain.PayWalletMoney;

public class PayWalletMoneyFactory {
    public static PayWalletMoneyEntity toEntity(PayWalletMoney payWalletMoney) {
        return PayWalletMoneyEntity.builder()
                .moneyRequestId(payWalletMoney.getChangeRequestId().getRequestId().toString())
                .targetMembershipId(payWalletMoney.getTargetMembershipId())
                .bankAccountNumber(payWalletMoney.getBankAccountNumber())
                .adjustAmount(payWalletMoney.getAdjustAmount())
                .linkedStatusIsValid(payWalletMoney.isLinkedStatusIsValid())
                .moneyChangType(payWalletMoney.getMoneyChangingType())
                .moneyChangingResultStatus(payWalletMoney.getMoneyChangingResultStatus())
                .build();
    }
}

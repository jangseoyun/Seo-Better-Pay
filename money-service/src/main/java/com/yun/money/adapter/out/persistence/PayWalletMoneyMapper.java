package com.yun.money.adapter.out.persistence;

import com.yun.money.domain.PayWalletMoney;
import org.springframework.stereotype.Component;

import static com.yun.money.domain.PayWalletMoney.*;

@Component
public class PayWalletMoneyMapper {
    public PayWalletMoney mapToDomainWalletMoney(PayWalletMoneyEntity entity) {
        return generatedPayWalletIncreaseMoney(
                new MoneyAdjustRequestId(entity.getMoneyRequestId()),
                new MembershipId(entity.getMembershipId()),
                new MemberName(entity.getMembershipName()),
                new LinkedBankCode(entity.getLinkedBankCode()),
                entity.getMoneyChangType(),
                new LinkedBankAccountNumber(entity.getLinkedBankAccountNumber()),
                new LinkedStatusIsValid(entity.isLinkedStatusIsValid()),
                new AdjustAmount(entity.getAdjustAmount()),
                entity.getMoneyChangingResultStatus()
        );
    }
}

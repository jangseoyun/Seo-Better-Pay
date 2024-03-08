package com.yun.money.adapter.out.persistence;

import com.yun.money.domain.PayWalletMoney;
import org.springframework.stereotype.Component;

import static com.yun.money.domain.PayWalletMoney.*;

@Component
public class PayWalletMoneyMapper {
    public PayWalletMoney mapToDomainWalletMoney(PayWalletMoneyEntity entity) {
        return generatedPayWalletChangeMoney(
                new MoneyChangingRequestId(entity.getMoneyRequestId()),
                new TargetMembershipId(entity.getTargetMembershipId()),
                new ChangingTypes(entity.getMoneyChangType()),
                new BankAccountNumber(entity.getBankAccountNumber()),
                new RequestAdjustAmount(entity.getAdjustAmount()),
                new ChangedMoneyStatus(entity.getMoneyChangingResultStatus()),
                new LinkedStatusIsValid(entity.isLinkedStatusIsValid())
        );
    }
}

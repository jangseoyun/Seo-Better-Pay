package com.yun.money.domain;

import com.yun.money.adapter.in.web.model.MoneyChangingResultStatus;
import org.springframework.stereotype.Component;

@Component
public class MoneyAmountCalculator {
    //계산하는 로직
    public MoneyChangingResultStatus checkAmountApproval(Integer moneyTotalAmount, Integer requestAmount) {
        if (moneyTotalAmount >= requestAmount || moneyTotalAmount == requestAmount) {
            return MoneyChangingResultStatus.SUCCEEDED;
        }

        if (moneyTotalAmount < requestAmount || moneyTotalAmount <= 0) {
            return MoneyChangingResultStatus.FAILED_NOT_ENOUGH_MONEY;
        }

        return MoneyChangingResultStatus.FAILED;
    }
}

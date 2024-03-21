package com.yun.money.domain;

import com.yun.money.adapter.in.web.model.MoneyAdjustingResultStatus;
import org.springframework.stereotype.Component;

@Component
public class MoneyAmountCalculator {
    //계산하는 로직
    public MoneyAdjustingResultStatus checkAmountApproval(Integer moneyTotalAmount, Integer requestAmount) {
        if (moneyTotalAmount >= requestAmount || moneyTotalAmount == requestAmount) {
            return MoneyAdjustingResultStatus.SUCCEEDED;
        }

        if (moneyTotalAmount < requestAmount || moneyTotalAmount <= 0) {
            return MoneyAdjustingResultStatus.FAILED_NOT_ENOUGH_MONEY;
        }

        return MoneyAdjustingResultStatus.FAILED;
    }
}

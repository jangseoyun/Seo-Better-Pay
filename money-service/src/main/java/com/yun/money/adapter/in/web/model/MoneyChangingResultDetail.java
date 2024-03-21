package com.yun.money.adapter.in.web.model;

public record MoneyChangingResultDetail(
        String moneyChangingRequestId,
        MoneyAdjustingType moneyChangingType,
        MoneyAdjustingResultStatus moneyChangingResultStatus,
        int amount
) {
}

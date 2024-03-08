package com.yun.money.adapter.in.web.model;

public record MoneyChangingResultDetail(
        String moneyChangingRequestId,
        MoneyChangingType moneyChangingType,
        MoneyChangingResultStatus moneyChangingResultStatus,
        int amount
) {
}

package com.yun.money.adapter.in.web.model;

import com.yun.money.application.port.in.IncreaseMoneyAmountCommand;

public record IncreaseMoneyAmountRequest(
        String moneyChangingRequestId,
        String targetMembershipId,
        String bankAccountNumber,
        Integer requestAdjustAmount
) {
    public IncreaseMoneyAmountCommand toCommand() {
        return IncreaseMoneyAmountCommand.of(
                moneyChangingRequestId,
                targetMembershipId,
                requestAdjustAmount,
                bankAccountNumber);
    }
}

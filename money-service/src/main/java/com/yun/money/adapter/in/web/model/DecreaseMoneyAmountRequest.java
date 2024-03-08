package com.yun.money.adapter.in.web.model;

import com.yun.money.application.port.in.DecreaseMoneyAmountCommand;

public record DecreaseMoneyAmountRequest(
        String moneyChangingRequestId,
        String targetMembershipId,
        String bankAccountNumber,
        Integer requestAdjustAmount
) {
    public DecreaseMoneyAmountCommand toCommand() {
        return DecreaseMoneyAmountCommand.of(
                moneyChangingRequestId, targetMembershipId, bankAccountNumber, requestAdjustAmount);
    }
}

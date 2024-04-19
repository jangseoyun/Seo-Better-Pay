package com.yun.money.application.port.factory;

import com.yun.money.adapter.in.web.model.MoneyAdjustingType;
import com.yun.money.adapter.in.web.model.RechargeMoneyAmountRequest;
import com.yun.money.application.port.in.RechargeMoneyAmountCommand;

import java.util.UUID;

public class RechargeCommandFactory {
    private static final MoneyAdjustingType RECHARGE_INCREASING = MoneyAdjustingType.INCREASING;

    public static RechargeMoneyAmountCommand newInstance(RechargeMoneyAmountRequest request) {
        return new RechargeMoneyAmountCommand(
                createRandomNumber(),
                request.membershipId(),
                request.membershipSeqNo(),
                request.memberName(),
                request.linkedBankCode(),
                request.linkedBankAccountNumber(),
                request.rechargeAmount(),
                RECHARGE_INCREASING
        );
    }

    private static String createRandomNumber() {
        return UUID.randomUUID().toString();
    }

}

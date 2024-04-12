package com.yun.money.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.money.adapter.in.web.model.MoneyAdjustingType;
import com.yun.money.adapter.in.web.model.RechargeMoneyAmountRequest;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RechargeMoneyAmountCommand extends SelfValidating<RechargeMoneyAmountRequest> {
    private final String moneyRechargeRequestId;
    private final String membershipId;
    private final String membershipSeqNo;
    private final String memberName;
    private final String linkedBankCode;
    private final String linkedBankAccountNumber;
    private final Integer rechargeAmount;
    private final MoneyAdjustingType moneyAdjustingType;
}

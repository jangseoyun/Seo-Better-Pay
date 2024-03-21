package com.yun.money.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.money.adapter.in.web.model.DecreaseMoneyAmountRequest;
import com.yun.money.adapter.in.web.model.MoneyAdjustingResultStatus;
import com.yun.money.adapter.in.web.model.MoneyAdjustingType;
import com.yun.money.domain.PayWalletMoney;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

import static com.yun.money.domain.PayWalletMoney.*;
import static com.yun.money.domain.PayWalletMoney.generatedPayWalletIncreaseMoney;

@Getter
@EqualsAndHashCode(callSuper = false)
public class DecreaseMoneyAmountCommand extends SelfValidating<DecreaseMoneyAmountRequest> {

    @NotNull
    @NotEmpty
    private final String membershipId;
    @NotNull
    @NotEmpty
    private final String memberName;
    @NotNull
    @NotEmpty
    private final String targetPaymentsInfo;
    @NotNull
    private final Integer decreaseAmount;
    private final MoneyAdjustingType moneyAdjustingType;

    private DecreaseMoneyAmountCommand(String membershipId,
                                      String memberName,
                                      String targetPaymentsInfo,
                                      Integer decreaseAmount,
                                      MoneyAdjustingType moneyAdjustingType) {
        this.membershipId = membershipId;
        this.memberName = memberName;
        this.targetPaymentsInfo = targetPaymentsInfo;
        this.decreaseAmount = decreaseAmount;
        this.moneyAdjustingType = moneyAdjustingType;
    }

    public static DecreaseMoneyAmountCommand of(String membershipId,
                                       String memberName,
                                       String targetPaymentsInfo,
                                       Integer decreaseAmount,
                                       MoneyAdjustingType moneyAdjustingType) {
        return new DecreaseMoneyAmountCommand(
                membershipId,
                memberName,
                targetPaymentsInfo,
                decreaseAmount,
                moneyAdjustingType);
    }



    public PayWalletMoney toPayWalletMoney(MoneyAdjustingResultStatus moneyChangingResultStatus) {
        return generatedPayWalletDecreaseMoney(
                new MoneyAdjustRequestId(UUID.randomUUID().toString()),
                new MembershipId(membershipId),
                new MemberName(memberName),
                MoneyAdjustingType.DECREASING,
                new LinkedStatusIsValid(true),
                new AdjustAmount(decreaseAmount),
                moneyChangingResultStatus);
    }
}

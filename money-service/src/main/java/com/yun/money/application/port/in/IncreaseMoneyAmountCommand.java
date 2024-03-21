package com.yun.money.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.money.adapter.in.web.model.IncreaseMoneyAmountRequest;
import com.yun.money.adapter.in.web.model.MoneyAdjustingResultStatus;
import com.yun.money.adapter.in.web.model.MoneyAdjustingType;
import com.yun.money.domain.PayWalletMoney;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

import static com.yun.money.domain.PayWalletMoney.*;

@Getter
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyAmountCommand extends SelfValidating<IncreaseMoneyAmountRequest> {

    @NotNull
    @NotEmpty
    private final String moneyIncreaseRequestId;
    @NotNull
    @NotEmpty
    private final String membershipId;
    @NotNull
    @NotEmpty
    private final String memberName;
    @NotNull
    @NotEmpty
    private final String linkedBankCode;
    @NotNull
    @NotEmpty
    private final String linkedBankAccountNumber;
    @NotNull
    private final Integer increaseAmount;
    private final MoneyAdjustingType moneyAdjustingType;

    @Builder
    public IncreaseMoneyAmountCommand(String moneyIncreaseRequestId,
                                      String membershipId,
                                      String memberName,
                                      String linkedBankCode,
                                      String linkedBankAccountNumber,
                                      Integer increaseAmount,
                                      MoneyAdjustingType moneyAdjustingType) {
        this.moneyIncreaseRequestId = moneyIncreaseRequestId;
        this.membershipId = membershipId;
        this.memberName = memberName;
        this.linkedBankCode = linkedBankCode;
        this.linkedBankAccountNumber = linkedBankAccountNumber;
        this.increaseAmount = increaseAmount;
        this.moneyAdjustingType = moneyAdjustingType;
    }

    public PayWalletMoney toPayWalletMoney(MoneyAdjustingResultStatus moneyAdjustingResultStatus) {
        return generatedPayWalletIncreaseMoney(
                new MoneyAdjustRequestId(UUID.randomUUID().toString()),
                new MembershipId(membershipId),
                new MemberName(memberName),
                new LinkedBankCode(linkedBankCode),
                MoneyAdjustingType.INCREASING,
                new LinkedBankAccountNumber(linkedBankAccountNumber),
                new LinkedStatusIsValid(true),
                new AdjustAmount(increaseAmount),
                moneyAdjustingResultStatus
        );
    }
}

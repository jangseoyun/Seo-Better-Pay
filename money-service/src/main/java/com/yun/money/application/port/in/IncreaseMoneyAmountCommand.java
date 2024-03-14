package com.yun.money.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.money.adapter.in.web.model.IncreaseMoneyAmountRequest;
import com.yun.money.adapter.in.web.model.MoneyChangingResultStatus;
import com.yun.money.adapter.in.web.model.MoneyChangingType;
import com.yun.money.domain.PayWalletMoney;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.yun.money.domain.PayWalletMoney.*;

@Getter
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyAmountCommand extends SelfValidating<IncreaseMoneyAmountRequest> {

    @NotNull
    @NotEmpty
    private final String moneyChangingRequestId;
    @NotNull
    @NotEmpty
    private final String targetMembershipId;
    @NotNull
    @NotEmpty
    private final String bankAccountNumber;
    @NotNull
    @NotEmpty
    private final Integer requestAdjustAmount;
    private final MoneyChangingType changingType;


    private IncreaseMoneyAmountCommand(String moneyChangingRequestId,
                                       String targetMembershipId,
                                       MoneyChangingType moneyChangingType,
                                       Integer requestAdjustAmount,
                                       String bankAccountNumber) {
        this.moneyChangingRequestId = moneyChangingRequestId;
        this.targetMembershipId = targetMembershipId;
        this.requestAdjustAmount = requestAdjustAmount;
        this.changingType = moneyChangingType;
        this.bankAccountNumber = bankAccountNumber;
    }

    public static IncreaseMoneyAmountCommand of(String moneyChangingRequestId,
                                                String targetMembershipId,
                                                Integer requestAdjustAmount,
                                                String bankAccountNumber) {
        return new IncreaseMoneyAmountCommand(moneyChangingRequestId,
                targetMembershipId,
                MoneyChangingType.INCREASING,
                requestAdjustAmount,
                bankAccountNumber);
    }

    public PayWalletMoney toPayWalletMoney(MoneyChangingResultStatus moneyChangingResultStatus) {
        return generatedPayWalletChangeMoney(
                new MoneyChangingRequestId(moneyChangingRequestId),
                new TargetMembershipId(targetMembershipId),
                new ChangingTypes(changingType),
                new BankAccountNumber(bankAccountNumber),
                new RequestAdjustAmount(requestAdjustAmount),
                new ChangedMoneyStatus(moneyChangingResultStatus),
                new LinkedStatusIsValid(true)
        );
    }
}

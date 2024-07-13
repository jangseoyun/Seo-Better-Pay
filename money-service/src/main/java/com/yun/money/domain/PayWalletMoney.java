package com.yun.money.domain;

import com.yun.money.adapter.in.web.model.MoneyAdjustingResultStatus;
import com.yun.money.adapter.in.web.model.MoneyAdjustingType;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PayWalletMoney {

    private final String moneyAdjustRequestId;
    private final String membershipId;
    private final String memberName;
    private final MoneyAdjustingType moneyAdjustingType;
    private final boolean linkedStatusIsValid;
    private final Integer adjustAmount;
    private final MoneyAdjustingResultStatus moneyChangingResultStatus;
    private String linkedBankCode;
    private String linkedBankAccountNumber;

    private PayWalletMoney(String moneyAdjustRequestId,
                           String membershipId,
                           String memberName,
                           MoneyAdjustingType moneyAdjustingType,
                           boolean linkedStatusIsValid,
                           Integer adjustAmount,
                           MoneyAdjustingResultStatus moneyChangingResultStatus) {
        this.moneyAdjustRequestId = moneyAdjustRequestId;
        this.membershipId = membershipId;
        this.memberName = memberName;
        this.moneyAdjustingType = moneyAdjustingType;
        this.linkedStatusIsValid = linkedStatusIsValid;
        this.adjustAmount = adjustAmount;
        this.moneyChangingResultStatus = moneyChangingResultStatus;
    }
    public static PayWalletMoney generatedPayWalletIncreaseMoney(
            MoneyAdjustRequestId moneyIncreaseRequestId,
            MembershipId membershipId,
            MemberName memberName,
            LinkedBankCode linkedBankCode,
            MoneyAdjustingType moneyAdjustingType,
            LinkedBankAccountNumber linkedBankAccountNumber,
            LinkedStatusIsValid linkedStatusIsValid,
            AdjustAmount increaseAmount,
            MoneyAdjustingResultStatus moneyAdjustingResultStatus
    ) {
        return new PayWalletMoney(
                moneyIncreaseRequestId.moneyAdjustRequestId,
                membershipId.membershipId,
                memberName.memberName,
                moneyAdjustingType,
                linkedStatusIsValid.linkedStatusIsValid,
                increaseAmount.adjustAmount,
                moneyAdjustingResultStatus,
                linkedBankCode.linkedBankCode,
                linkedBankAccountNumber.linkedBankAccountNumber
        );
    }

    public static PayWalletMoney generatedPayWalletDecreaseMoney(
            MoneyAdjustRequestId moneyDecreaseRequestId,
            MembershipId membershipId,
            MemberName memberName,
            MoneyAdjustingType moneyAdjustingType,
            LinkedStatusIsValid linkedStatusIsValid,
            AdjustAmount decreaseAmount,
            MoneyAdjustingResultStatus moneyAdjustingResultStatus
            ) {
        return new PayWalletMoney(
                moneyDecreaseRequestId.moneyAdjustRequestId,
                membershipId.membershipId,
                memberName.memberName,
                moneyAdjustingType,
                linkedStatusIsValid.linkedStatusIsValid,
                decreaseAmount.adjustAmount,
                moneyAdjustingResultStatus
        );
    }

    @Value
    public static class MoneyAdjustRequestId {
        String moneyAdjustRequestId;

        public MoneyAdjustRequestId(String value) {
            this.moneyAdjustRequestId = value;
        }
    }

    @Value
    public static class MembershipId {
        String membershipId;

        public MembershipId(String value) {
            this.membershipId = value;
        }
    }

    @Value
    public static class MemberName {
        String memberName;

        public MemberName(String value) {
            this.memberName = value;
        }
    }

    @Value
    public static class LinkedBankCode {
        String linkedBankCode;

        public LinkedBankCode(String value) {
            this.linkedBankCode = value;
        }
    }

    @Value
    public static class LinkedBankAccountNumber {
        String linkedBankAccountNumber;

        public LinkedBankAccountNumber(String value) {
            this.linkedBankAccountNumber = value;
        }
    }

    @Value
    public static class LinkedStatusIsValid {
        boolean linkedStatusIsValid;

        public LinkedStatusIsValid(boolean value) {
            this.linkedStatusIsValid = value;
        }
    }

    @Value
    public static class AdjustAmount {
        Integer adjustAmount;

        public AdjustAmount(Integer value) {
            this.adjustAmount = value;
        }
    }

    @Value
    public static class AdjustMoneyStatus {
        MoneyAdjustingResultStatus changingMoneyStatus;

        public AdjustMoneyStatus(MoneyAdjustingResultStatus value) {
            this.changingMoneyStatus = value;
        }
    }
}

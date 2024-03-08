package com.yun.money.domain;

import com.yun.money.adapter.in.web.model.MoneyChangingResultStatus;
import com.yun.money.adapter.in.web.model.MoneyChangingType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PayWalletMoney {

    private final String moneyChangingRequestId;
    // 어떤 고객의 증액/감액 요청을 요청했는지의 멤버 정보
    private final String targetMembershipId;
    // 요청에 대한 타입(증액/감액)
    private final MoneyChangingType moneyChangingType;
    // 증액 또는 감액에 대한 계좌
    private final String bankAccountNumber;
    // 머니 변액 요청에 대한 상태
    private final boolean linkedStatusIsValid;
    private final Integer adjustAmount;
    private final MoneyChangingResultStatus moneyChangingResultStatus;
    private final ChangeRequestId changeRequestId;

    public static PayWalletMoney generatedPayWalletChangeMoney(
            MoneyChangingRequestId moneyChangingRequestId,
            TargetMembershipId targetMembershipId,
            ChangingTypes moneyChangingType,
            BankAccountNumber bankAccountNumber,
            RequestAdjustAmount requestAdjustAmount,
            ChangedMoneyStatus moneyChangingResultStatus,
            LinkedStatusIsValid linkedStatusIsValid
    ) {
        return new PayWalletMoney(
                moneyChangingRequestId.moneyChangingRequestId,
                targetMembershipId.targetMembershipId,
                moneyChangingType.changingType,
                bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid.linkedStatusIsValid,
                requestAdjustAmount.requestAdjustAmount,
                moneyChangingResultStatus.changingMoneyStatus,
                new ChangeRequestId()
        );
    }

    @Value
    public static class MoneyChangingRequestId {
        String moneyChangingRequestId;

        public MoneyChangingRequestId(String value) {
            this.moneyChangingRequestId = value;
        }
    }

    @Value
    public static class TargetMembershipId {
        String targetMembershipId;

        public TargetMembershipId(String value) {
            this.targetMembershipId = value;
        }
    }

    @Value
    public static class ChangingTypes {
        MoneyChangingType changingType;

        public ChangingTypes(MoneyChangingType value) {
            this.changingType = value;
        }
    }

    @Value
    public static class BankAccountNumber {
        String bankAccountNumber;

        public BankAccountNumber(String value) {
            this.bankAccountNumber = value;
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
    public static class RequestAdjustAmount {
        Integer requestAdjustAmount;

        public RequestAdjustAmount(Integer value) {
            this.requestAdjustAmount = value;
        }
    }

    @Value
    public static class ChangeRequestId {
        UUID requestId;

        public ChangeRequestId() {
            this.requestId = UUID.randomUUID();
        }
    }

    @Value
    public static class ChangedMoneyStatus {
        MoneyChangingResultStatus changingMoneyStatus;

        public ChangedMoneyStatus(MoneyChangingResultStatus value) {
            this.changingMoneyStatus = value;
        }
    }
}

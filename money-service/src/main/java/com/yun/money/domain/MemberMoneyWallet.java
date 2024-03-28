package com.yun.money.domain;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoneyWallet {
    private final String membershipId;
    private final int moneyTotalAmount;
    private final String moneyAggregateIdentifier;

    public static MemberMoneyWallet generateMemberMoneyWallet(MembershipId membershipId,
                                                              MoneyTotalAmount moneyTotalAmount,
                                                              MoneyAggregateIdentifier aggregateIdentifier) {
        return new MemberMoneyWallet(membershipId.membershipId, moneyTotalAmount.moneyTotalAmount, aggregateIdentifier.moneyAggregateIdentifier);
    }

    @Value
    public static class MembershipId {
        String membershipId;

        public MembershipId(String value) {
            this.membershipId = value;
        }
    }

    @Value
    public static class MoneyTotalAmount {
        int moneyTotalAmount;

        public MoneyTotalAmount(int value) {
            this.moneyTotalAmount = value;
        }
    }

    @Value
    public static class MoneyAggregateIdentifier {
        String moneyAggregateIdentifier;

        public MoneyAggregateIdentifier(String value) {
            this.moneyAggregateIdentifier = value;
        }
    }
}

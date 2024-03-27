package com.yun.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoneyWallet {
    private final String membershipId;
    private final int moneyTotalAmount;

    public static MemberMoneyWallet generateMemberMoneyWallet(MembershipId membershipId,
                                                              MoneyTotalAmount moneyTotalAmount) {
        return new MemberMoneyWallet(membershipId.membershipId, moneyTotalAmount.moneyTotalAmount);
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
}

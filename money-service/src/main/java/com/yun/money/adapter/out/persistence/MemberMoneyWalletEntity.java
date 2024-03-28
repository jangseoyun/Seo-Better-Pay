package com.yun.money.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_member_money_wallet")
public class MemberMoneyWalletEntity {
    @Id
    @Column(name = "membership_id")
    private String membershipId;
    @Column(name = "money_aggregate_identifier")
    private String moneyAggregateIdentifier;
    @Column(name = "money_total_amount")
    private int moneyTotalAmount;

    private MemberMoneyWalletEntity(String membershipId, String moneyAggregateIdentifier, int moneyTotalAmount) {
        this.membershipId = membershipId;
        this.moneyAggregateIdentifier = moneyAggregateIdentifier;
        this.moneyTotalAmount = moneyTotalAmount;
    }

    public static MemberMoneyWalletEntity create(String membershipId, String moneyAggregateIdentifier, int moneyTotalAmount) {
        return new MemberMoneyWalletEntity(membershipId, moneyAggregateIdentifier, moneyTotalAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberMoneyWalletEntity that)) return false;
        return Objects.equals(membershipId, that.membershipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membershipId);
    }
}

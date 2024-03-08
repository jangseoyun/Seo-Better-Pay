package com.yun.money.adapter.out.persistence;

import com.yun.money.adapter.in.web.model.MoneyChangingResultStatus;
import com.yun.money.adapter.in.web.model.MoneyChangingType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_pay_wallet_money")
public class PayWalletMoneyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_wallet_money_id")
    private Long id;
    @Column(name = "money_request_id")
    private String moneyRequestId;
    @Column(name = "target_membership_id")
    private String targetMembershipId;
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
    @Column(name = "adjust_amount")
    private Integer adjustAmount;
    @Column(name = "linked_status_valid")
    private boolean linkedStatusIsValid;
    @Enumerated(EnumType.STRING)
    @Column(name = "money_change_type")
    private MoneyChangingType moneyChangType;
    @Enumerated(EnumType.STRING)
    @Column(name = "money_changed_result_status")
    private MoneyChangingResultStatus moneyChangingResultStatus;

    @Builder
    public PayWalletMoneyEntity(String moneyRequestId,
                                String targetMembershipId,
                                String bankAccountNumber,
                                Integer adjustAmount,
                                boolean linkedStatusIsValid,
                                MoneyChangingType moneyChangType,
                                MoneyChangingResultStatus moneyChangingResultStatus) {
        this.moneyRequestId = moneyRequestId;
        this.targetMembershipId = targetMembershipId;
        this.bankAccountNumber = bankAccountNumber;
        this.adjustAmount = adjustAmount;
        this.linkedStatusIsValid = linkedStatusIsValid;
        this.moneyChangType = moneyChangType;
        this.moneyChangingResultStatus = moneyChangingResultStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayWalletMoneyEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

package com.yun.money.adapter.out.persistence;

import com.yun.money.adapter.in.web.model.MoneyAdjustingResultStatus;
import com.yun.money.adapter.in.web.model.MoneyAdjustingType;
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
    @Column(name = "membership_id")
    private String membershipId;
    @Column(name = "membership_name")
    private String membershipName;
    @Column(name = "linked_bank_code")
    private String linkedBankCode;
    @Column(name = "linked_bank_account_number")
    private String linkedBankAccountNumber;
    @Column(name = "adjust_amount")
    private Integer adjustAmount;
    @Column(name = "linked_status_valid")
    private boolean linkedStatusIsValid;
    @Enumerated(EnumType.STRING)
    @Column(name = "money_change_type")
    private MoneyAdjustingType moneyChangType;
    @Enumerated(EnumType.STRING)
    @Column(name = "money_changed_result_status")
    private MoneyAdjustingResultStatus moneyChangingResultStatus;

    @Builder
    public PayWalletMoneyEntity(Long id,
                                String moneyRequestId,
                                String membershipId,
                                String membershipName,
                                String linkedBankCode,
                                String linkedBankAccountNumber,
                                Integer adjustAmount,
                                boolean linkedStatusIsValid,
                                MoneyAdjustingType moneyChangType,
                                MoneyAdjustingResultStatus moneyChangingResultStatus) {
        this.id = id;
        this.moneyRequestId = moneyRequestId;
        this.membershipId = membershipId;
        this.membershipName = membershipName;
        this.linkedBankCode = linkedBankCode;
        this.linkedBankAccountNumber = linkedBankAccountNumber;
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

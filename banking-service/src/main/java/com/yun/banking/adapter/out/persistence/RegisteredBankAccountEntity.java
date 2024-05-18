package com.yun.banking.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_register_bank_account")
public class  RegisteredBankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registered_bank_account_id")
    private Long id;
    @Column(name = "membership_id")
    private String membershipId;
    @Column(name = "bank_account_code")
    private String bankAccountCode;
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
    @Column(name = "user_social_number")
    private String userSocialNumber;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_ci")
    private String userCi;
    @Column(name = "scope")
    private String scope;
    @Column(name = "linked_status_valid")
    private boolean linkedStatusIsValid;
    @Column(name = "aggregate_identifier")
    private String aggregateIdentifier;

    @Builder
    public RegisteredBankAccountEntity(Long id,
                                       String membershipId,
                                       String bankAccountCode,
                                       String bankAccountNumber,
                                       String userSocialNumber,
                                       String userName,
                                       String userCi,
                                       String scope,
                                       boolean linkedStatusIsValid,
                                       String aggregateIdentifier) {
        this.id = id;
        this.membershipId = membershipId;
        this.bankAccountCode = bankAccountCode;
        this.bankAccountNumber = bankAccountNumber;
        this.userSocialNumber = userSocialNumber;
        this.userName = userName;
        this.userCi = userCi;
        this.scope = scope;
        this.linkedStatusIsValid = linkedStatusIsValid;
        this.aggregateIdentifier = aggregateIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisteredBankAccountEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

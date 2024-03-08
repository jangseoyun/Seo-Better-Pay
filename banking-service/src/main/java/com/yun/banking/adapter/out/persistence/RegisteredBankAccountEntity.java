package com.yun.banking.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_register_bank_account")
public class RegisteredBankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registered_bank_account_id")
    private Long id;
    @Column(name = "membership_id")
    private String membershipId;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
    @Column(name = "linked_status_valid")
    private boolean linkedStatusIsValid;

    private RegisteredBankAccountEntity(Long id, String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.id = id;
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
    }

    public static RegisteredBankAccountEntity of(String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        return new RegisteredBankAccountEntity(null, membershipId, bankName, bankAccountNumber, linkedStatusIsValid);
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

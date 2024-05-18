package com.yun.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegisteredBankAccountJpaRepository extends JpaRepository<RegisteredBankAccountEntity, Long> {
    @Query(value = "select bank from RegisteredBankAccountEntity bank where bank.membershipId = :membershipId")
    List<RegisteredBankAccountEntity> getLinkedBankAccount(@Param("membershipId") String membershipId);

    @Query(value = "select bank from RegisteredBankAccountEntity bank where bank.bankAccountNumber = :registerAccountNum")
    Optional<RegisteredBankAccountEntity> checkedBankAccountStatus(@Param("registerAccountNum") String registerAccountNum);
}

package com.yun.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PayWalletMoneyJpaRepository extends JpaRepository<PayWalletMoneyEntity, Long> {
    @Query(value = "select sum(pay.adjustAmount) from PayWalletMoneyEntity pay where pay.bankAccountNumber = :bankAccountNumber and pay.moneyChangingResultStatus = 'SUCCEEDED'")
    Integer moneyTotalAmount(@Param("bankAccountNumber") String bankAccountNumber);
}

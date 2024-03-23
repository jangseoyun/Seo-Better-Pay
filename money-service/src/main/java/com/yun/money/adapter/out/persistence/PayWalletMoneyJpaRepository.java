package com.yun.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PayWalletMoneyJpaRepository extends JpaRepository<PayWalletMoneyEntity, Long> {
    @Query(value = "select sum(pay.adjustAmount) from PayWalletMoneyEntity pay where pay.membershipId = :membershipId and pay.moneyChangingResultStatus = 'SUCCEEDED'")
    Integer moneyTotalAmount(@Param("membershipId") String membershipId);

    @Query(value = "select money from PayWalletMoneyEntity money where money.membershipId = :membershipId and money.moneyChangType = 'INCREASING'")
    List<PayWalletMoneyEntity> getIncreaseMoneyHistory(@Param("membershipId") String membershipId);

    @Query(value = "select money from PayWalletMoneyEntity money where money.membershipId = :membershipId and money.moneyChangType = 'DECREASING'")
    List<PayWalletMoneyEntity> getDecreaseMoneyHistory(@Param("membershipId") String membershipId);
}

package com.yun.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMoneyWalletJpaRepository extends JpaRepository<MemberMoneyWalletEntity, String> {
    @Query(value = "select money from MemberMoneyWalletEntity money where money.membershipId in :membershipIds")
    List<MemberMoneyWalletEntity> findByMembershipIdList(@Param("membershipIds") List<String> membershipIds);
}

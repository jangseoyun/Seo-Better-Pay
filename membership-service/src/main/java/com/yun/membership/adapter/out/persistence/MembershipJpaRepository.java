package com.yun.membership.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MembershipJpaRepository extends JpaRepository<MembershipEntity, String> {
    @Query(value = "select membership from MembershipEntity membership where membership.address = :address")
    List<MembershipEntity> findAllByMembershipAddress(@Param("address") String address);
}

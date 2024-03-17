package com.yun.membership.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipJpaRepository extends JpaRepository<MembershipEntity, String> {
}

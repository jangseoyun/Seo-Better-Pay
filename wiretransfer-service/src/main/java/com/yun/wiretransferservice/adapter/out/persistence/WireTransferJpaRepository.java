package com.yun.wiretransferservice.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WireTransferJpaRepository extends JpaRepository<WireTransferEntity, Long> {
    @Query(value = "select transfer from WireTransferEntity transfer where transfer.fromMembershipId = :fromMembershipId")
    Page<WireTransferEntity> findByFromMembershipId(@Param("fromMembershipId") String fromMembershipId, Pageable pageable);
}

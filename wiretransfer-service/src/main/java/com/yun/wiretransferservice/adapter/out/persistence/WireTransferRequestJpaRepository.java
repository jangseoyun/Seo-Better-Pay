package com.yun.wiretransferservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WireTransferRequestJpaRepository extends JpaRepository<WireTransferRequestEntity, String> {
}

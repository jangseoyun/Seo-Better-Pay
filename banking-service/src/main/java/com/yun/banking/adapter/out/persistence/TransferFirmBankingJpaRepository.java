package com.yun.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TransferFirmBankingJpaRepository extends JpaRepository<TransferFirmBankingEntity, Long> {
    @Query(value = "select transfer from TransferFirmBankingEntity transfer where transfer.transferAggregateIdentifier = :transferAggregateIdentifier")
    Optional<TransferFirmBankingEntity> findByTransferAggregateIdentifier(@Param("transferAggregateIdentifier") String transferAggregateIdentifier);
}

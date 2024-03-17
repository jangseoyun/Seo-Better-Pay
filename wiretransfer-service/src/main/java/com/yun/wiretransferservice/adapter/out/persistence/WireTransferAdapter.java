package com.yun.wiretransferservice.adapter.out.persistence;

import com.yun.common.PersistenceAdapter;
import com.yun.wiretransferservice.application.port.out.ReadWireTransferHistoryPort;
import com.yun.wiretransferservice.application.port.out.WireTransferPort;
import com.yun.wiretransferservice.domain.WireTransfer;
import com.yun.wiretransferservice.domain.WireTransferRequestDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@PersistenceAdapter
@RequiredArgsConstructor
public class WireTransferAdapter implements WireTransferPort, ReadWireTransferHistoryPort {

    private final WireTransferJpaRepository wireTransferJpaRepository;
    private final WireTransferMapper mapper;

    @Override
    public WireTransfer saveFinalWireTransfer(WireTransferRequestDomain wireTransferRequestDomain) {
        WireTransferEntity wireTransferEntity = wireTransferJpaRepository.save(mapper.toEntity(wireTransferRequestDomain));
        return mapper.mapToDomainEntity(wireTransferEntity);
    }

    @Override
    public Page<WireTransfer> findWireTransferHistory(String fromMembershipId, Pageable pageable) {
        return wireTransferJpaRepository.findByFromMembershipId(fromMembershipId, pageable)
                .map(history -> mapper.mapToDomainEntity(history));
    }
}

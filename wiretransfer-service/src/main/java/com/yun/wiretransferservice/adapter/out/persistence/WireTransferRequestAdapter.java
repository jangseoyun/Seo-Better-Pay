package com.yun.wiretransferservice.adapter.out.persistence;

import com.yun.common.PersistenceAdapter;
import com.yun.wiretransferservice.application.port.out.WireTransferRequestPort;
import com.yun.wiretransferservice.domain.WireTransferRequestDomain;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class WireTransferRequestAdapter implements WireTransferRequestPort {

    private final WireTransferRequestJpaRepository wireTransferRequestJpaRepository;
    private final WireTransferRequestMapper mapper;

    @Override
    public WireTransferRequestDomain createWireTransferHistory(WireTransferRequestDomain wireTransferRequestDomain) {
        WireTransferRequestEntity savedWireTransferRequest = wireTransferRequestJpaRepository.save(mapper.from(wireTransferRequestDomain));
        return mapper.mapToDomainEntity(savedWireTransferRequest);
    }
}

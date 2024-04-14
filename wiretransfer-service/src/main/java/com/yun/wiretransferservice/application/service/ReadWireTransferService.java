package com.yun.wiretransferservice.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.wiretransferservice.application.port.in.ReadWIreTransferUseCase;
import com.yun.wiretransferservice.application.port.out.ReadWireTransferHistoryPort;
import com.yun.wiretransferservice.domain.WireTransfer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadWireTransferService implements ReadWIreTransferUseCase {

    private final ReadWireTransferHistoryPort readWireTransferHistoryPort;

    @Override
    public Page<WireTransfer> findWireTransferHistory(String fromMembershipId, Pageable pageable) {
        return readWireTransferHistoryPort.findWireTransferHistory(fromMembershipId, pageable);
    }
}

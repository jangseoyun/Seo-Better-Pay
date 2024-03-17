package com.yun.wiretransferservice.application.port.in;

import com.yun.wiretransferservice.domain.WireTransfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReadWIreTransferUseCase {
    Page<WireTransfer> findWireTransferHistory(String fromMembershipId, Pageable pageable);
}

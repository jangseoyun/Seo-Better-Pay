package com.yun.wiretransferservice.application.port.out;

import com.yun.wiretransferservice.domain.WireTransfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReadWireTransferHistoryPort {
    Page<WireTransfer> findWireTransferHistory(String fromMembershipId, Pageable pageable);
}

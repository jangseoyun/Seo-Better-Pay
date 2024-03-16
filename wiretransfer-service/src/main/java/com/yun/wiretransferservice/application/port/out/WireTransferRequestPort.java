package com.yun.wiretransferservice.application.port.out;

import com.yun.wiretransferservice.domain.WireTransferRequestDomain;

public interface WireTransferRequestPort {
    WireTransferRequestDomain createWireTransferHistory(WireTransferRequestDomain wireTransferRequestDomain);
}

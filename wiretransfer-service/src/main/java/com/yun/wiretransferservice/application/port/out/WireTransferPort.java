package com.yun.wiretransferservice.application.port.out;

import com.yun.wiretransferservice.domain.WireTransfer;
import com.yun.wiretransferservice.domain.WireTransferRequestDomain;

public interface WireTransferPort {
    WireTransfer saveFinalWireTransfer(WireTransferRequestDomain wireTransferRequestDomain);
}

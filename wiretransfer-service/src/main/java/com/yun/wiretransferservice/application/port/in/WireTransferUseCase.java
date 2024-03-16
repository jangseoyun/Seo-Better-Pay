package com.yun.wiretransferservice.application.port.in;

import com.yun.wiretransferservice.domain.WireTransfer;

public interface WireTransferUseCase {
    WireTransfer requestWireTransfer(WireTransferCommand command);
}

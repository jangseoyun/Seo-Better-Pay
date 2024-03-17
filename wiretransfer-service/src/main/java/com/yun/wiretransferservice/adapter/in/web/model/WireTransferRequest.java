package com.yun.wiretransferservice.adapter.in.web.model;

import com.yun.wiretransferservice.application.port.in.WireTransferCommand;

public record WireTransferRequest(
    String fromMembershipId,
    String toMembershipId,
    String toBankName,
    String toBankAccountNumber,
    String wireTransferType,
    int wireTransferAmount
) {
    public WireTransferCommand toCommand() {
        return WireTransferCommand.of(
                fromMembershipId,
                toMembershipId,
                toBankName,
                toBankAccountNumber,
                wireTransferAmount,
                wireTransferType,
                true
        );
    }
}

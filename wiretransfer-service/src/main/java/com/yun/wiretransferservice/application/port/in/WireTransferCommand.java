package com.yun.wiretransferservice.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.wiretransferservice.adapter.in.web.model.WireTransferRequest;
import com.yun.wiretransferservice.adapter.in.web.model.WireTransferType;
import com.yun.wiretransferservice.domain.WireTransferRequestDomain;
import com.yun.wiretransferservice.domain.WireTransferRequestStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(callSuper = false)
public class WireTransferCommand extends SelfValidating<WireTransferRequest> {
    @NotNull
    @NotEmpty
    private final String fromMembershipId;
    private final String toMembershipId;
    @NotNull
    @NotEmpty
    private final String toBankName;
    @NotNull
    @NotEmpty
    private final String toBankAccountNumber;
    @NotNull
    @NotEmpty
    private final int wireTransferAmount;
    @NotNull
    @NotEmpty
    private final WireTransferType wireTransferType;
    private final boolean isValid;

    private WireTransferCommand(String fromMembershipId,
                                String toMembershipId,
                                String toBankName,
                                String toBankAccountNumber,
                                int wireTransferAmount,
                                WireTransferType wireTransferType,
                                boolean isValid) {
        this.fromMembershipId = fromMembershipId;
        this.toMembershipId = toMembershipId;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.wireTransferAmount = wireTransferAmount;
        this.wireTransferType = wireTransferType;
        this.isValid = isValid;

        this.validateSelf();
    }

    public static WireTransferCommand of(String fromMembershipId,
                                String toMembershipId,
                                String toBankName,
                                String toBankAccountNumber,
                                int wireTransferAmount,
                                WireTransferType wireTransferType,
                                boolean isValid) {
        return new WireTransferCommand(fromMembershipId,
                toMembershipId,
                toBankName,
                toBankAccountNumber,
                wireTransferAmount,
                wireTransferType,
                isValid);
    }

    public WireTransferRequestDomain toDomain() {
        return WireTransferRequestDomain.generatedWireTransfer(
                new WireTransferRequestDomain.WireTransferRequestId(UUID.randomUUID().toString()),
                new WireTransferRequestDomain.FromMembershipId(fromMembershipId),
                new WireTransferRequestDomain.ToMembershipId(toMembershipId),
                new WireTransferRequestDomain.ToBankName(toBankName),
                new WireTransferRequestDomain.ToBankAccountNumber(toBankAccountNumber),
                WireTransferType.SEOBETTERPAY_MEMBER,
                new WireTransferRequestDomain.WireTransferAmount(wireTransferAmount),
                WireTransferRequestStatus.PENDING_REQUEST
        );
    }

}

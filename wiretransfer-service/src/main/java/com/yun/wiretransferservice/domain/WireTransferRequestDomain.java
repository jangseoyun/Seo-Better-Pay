package com.yun.wiretransferservice.domain;

import com.yun.wiretransferservice.adapter.in.web.model.WireTransferType;
import jakarta.persistence.criteria.From;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

/**
 * 송금 요청건에 대한 상태 정보
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WireTransferRequestDomain {

    private final String wireTransferRequestId;
    private final String fromMembershipId;
    private final String toMembershipId;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final WireTransferType wireTransferType;
    private final int wireTransferAmount;
    private final WireTransferRequestStatus wireTransferRequestStatus;

    public static WireTransferRequestDomain generatedWireTransfer(
            WireTransferRequestId wireTransferRequestId,
            FromMembershipId fromMembershipId,
            ToMembershipId toMembershipId,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            WireTransferType wireTransferType,
            WireTransferAmount wireTransferAmount,
            WireTransferRequestStatus wireTransferRequestStatus
    ) {
        return new WireTransferRequestDomain(
                wireTransferRequestId.wireTransferRequestId,
                fromMembershipId.getFromMembershipId(),
                toMembershipId.toMembershipId,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                wireTransferType,
                wireTransferAmount.wireTransferAmount,
                wireTransferRequestStatus
        );
    }

    @Value
    public static class WireTransferRequestId {
        String wireTransferRequestId;

        public WireTransferRequestId(String value) {
            this.wireTransferRequestId = value;
        }
    }

    @Value
    public static class FromMembershipId {
        String fromMembershipId;

        public FromMembershipId(String value) {
            this.fromMembershipId = value;
        }
    }

    @Value
    public static class ToMembershipId {
        String toMembershipId;

        public ToMembershipId(String value) {
            this.toMembershipId = value;
        }
    }

    @Value
    public static class ToBankName {
        String toBankName;

        public ToBankName(String value) {
            this.toBankName = value;
        }
    }

    @Value
    public static class ToBankAccountNumber {
        String toBankAccountNumber;

        public ToBankAccountNumber(String value) {
            this.toBankAccountNumber = value;
        }
    }

    @Value
    public static class WireTransferAmount {
        int wireTransferAmount;

        public WireTransferAmount(int value) {
            this.wireTransferAmount = value;
        }
    }

    @Value
    public static class WireTransferStatus {
        WireTransferRequestStatus wireTransferStatus;

        public WireTransferStatus(WireTransferRequestStatus value) {
            this.wireTransferStatus = value;
        }
    }
}

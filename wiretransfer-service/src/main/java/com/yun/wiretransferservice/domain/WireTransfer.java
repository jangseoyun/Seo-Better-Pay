package com.yun.wiretransferservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * 송금 완료건에 대한 정보
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WireTransfer {

    private final String wireTransferRequestId;
    private final String fromMembershipId;
    private final String toMembershipId;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final int wireTransferAmount;
    private final boolean isValid;

    public static WireTransfer generatedWireTransfer(
            WireTransferRequestId wireTransferRequestId,
            FromMembershipId fromMembershipId,
            ToMembershipId toMembershipId,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            WireTransferAmount wireTransferAmount,
            IsValid isValid
    ) {
        return new WireTransfer(
                wireTransferRequestId.wireTransferRequestId,
                fromMembershipId.fromMembershipId,
                toMembershipId.toMembershipId,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                wireTransferAmount.wireTransferAmount,
                isValid.isValid
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
    public static class IsValid {
        boolean isValid;

        public IsValid(boolean value) {
            this.isValid = value;
        }
    }
}

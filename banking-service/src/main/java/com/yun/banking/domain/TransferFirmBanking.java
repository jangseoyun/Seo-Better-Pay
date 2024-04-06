package com.yun.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

/**
 * 금액은 원화를 기준으로 한다.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferFirmBanking {

    private final String transferFirmBankingId;
    private final String fromBankName;
    private final String fromBankAccountNumber;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final int transferAmount;
    private final TransferRequestStatusEnum transferRequestStatus;
    private final TransferRequestId requestId;
    private final String aggregateIdentifier;

    public static TransferFirmBanking generateTransferFirmBaking(
            TransferFirmBankingId transferFirmBankingId,
            FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            TransferAmount transferAmount,
            TransferRequestStatus transferRequestStatus,
            TransferAggregateIdentifier transferAggregateIdentifier
    ) {
        return new TransferFirmBanking(
                transferFirmBankingId.transferFirmBankingId,
                fromBankName.fromBankName,
                fromBankAccountNumber.fromBankAccountNumber,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                transferAmount.transferAmount,
                transferRequestStatus.transferRequestStatus,
                new TransferRequestId(),
                transferAggregateIdentifier.aggregateIdentifier);
    }

    @Value
    public static class TransferFirmBankingId {
        String transferFirmBankingId;

        public TransferFirmBankingId(String value) {
            this.transferFirmBankingId = value;
        }
    }

    @Value
    public static class FromBankName {
        String fromBankName;

        public FromBankName(String value) {
            this.fromBankName = value;
        }
    }

    @Value
    public static class FromBankAccountNumber {
        String fromBankAccountNumber;

        public FromBankAccountNumber(String value) {
            this.fromBankAccountNumber = value;
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
    public static class TransferAmount {
        int transferAmount;

        public TransferAmount(int value) {
            this.transferAmount = value;
        }
    }

    @Value
    public static class TransferRequestStatus {
        TransferRequestStatusEnum transferRequestStatus;

        public TransferRequestStatus(TransferRequestStatusEnum value) {
            this.transferRequestStatus = value;
        }
    }

    @Value
    public static class TransferRequestId {
        UUID requestId;

        public TransferRequestId() {
            this.requestId = UUID.randomUUID();
        }
    }

    @Value
    public static class TransferAggregateIdentifier {
        String aggregateIdentifier;
        public TransferAggregateIdentifier(String value) {
            this.aggregateIdentifier = value;
        }
    }

}

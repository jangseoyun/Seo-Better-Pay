package com.yun.banking.adapter.out.persistence;

import com.yun.banking.domain.TransferFirmBanking;
import com.yun.banking.domain.TransferRequestStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "t_transfer_firm_banking")
public class TransferFirmBankingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_firm_banking_id")
    private Long id;
/*    @Column(name = "membership_id")
    private String membershipId;*/
    @Column(name = "from_bank_name")
    private String fromBankName;
    @Column(name = "from_bank_account_number")
    private String fromBankAccountNumber;
    @Column(name = "to_bank_name")
    private String toBankName;
    @Column(name = "to_bank_account_number")
    private String toBankAccountNumber;
    @Column(name = "transfer_amount")
    private int transferAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "transfer_request_status")
    private TransferRequestStatusEnum transferRequestStatus;
    @Column(name = "transfer_aggregate_identifier")
    private String transferAggregateIdentifier;

    public static TransferFirmBankingEntity of(TransferFirmBanking transferFirmBanking) {
        return new TransferFirmBankingEntity(null,
                transferFirmBanking.getFromBankName(),
                transferFirmBanking.getFromBankAccountNumber(),
                transferFirmBanking.getToBankName(),
                transferFirmBanking.getToBankAccountNumber(),
                transferFirmBanking.getTransferAmount(),
                TransferRequestStatusEnum.REQUEST,
                transferFirmBanking.getAggregateIdentifier());
    }

    public void succeedProcessingTransferFirmBanking(TransferRequestStatusEnum transferRequestStatus) {
        this.transferRequestStatus = transferRequestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferFirmBankingEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

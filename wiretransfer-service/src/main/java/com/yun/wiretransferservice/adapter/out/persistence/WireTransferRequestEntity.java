package com.yun.wiretransferservice.adapter.out.persistence;

import com.yun.wiretransferservice.adapter.in.web.model.WireTransferType;
import com.yun.wiretransferservice.domain.WireTransferRequestStatus;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "t_request_wire_transfer")
public class WireTransferRequestEntity {
    @Id
    @Column(name = "request_wire_transfer_id")
    private String requestId;
    @Column(name = "from_membership_id")
    private String fromMembershipId;
    @Column(name = "to_membership_id")
    private String toMembershipId;
    @Column(name = "to_bank_name")
    private String toBankName;
    @Column(name = "to_bank_account_number")
    private String toBankAccountNumber;
    @Column(name = "wire_transfer_amount")
    private int wireTransferAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "wire_transfer_type")
    private WireTransferType wireTransferType;
    @Enumerated(EnumType.STRING)
    @Column(name = "wire_transfer_request_status")
    private WireTransferRequestStatus wireTransferRequestStatus;
    @Column(name = "is_valid")
    private boolean isValid;

    public static WireTransferRequestEntity of(String id,
                                               String fromMembershipId,
                                               String toMembershipId,
                                               String toBankName,
                                               String toBankAccountNumber,
                                               int wireTransferAmount,
                                               WireTransferType wireTransferType,
                                               WireTransferRequestStatus wireTransferRequestStatus,
                                               boolean isValid) {
        return new WireTransferRequestEntity(
                id,
                fromMembershipId,
                toMembershipId,
                toBankName,
                toBankAccountNumber,
                wireTransferAmount,
                wireTransferType,
                wireTransferRequestStatus,
                isValid);
    }
}

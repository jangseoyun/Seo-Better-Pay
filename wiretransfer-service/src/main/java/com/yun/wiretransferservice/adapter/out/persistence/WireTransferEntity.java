package com.yun.wiretransferservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "t_wire_transfer")
public class WireTransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wire_transfer_id")
    private Long id;
    @Column(name = "request_id")
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
    @Column(name = "is_valid")
    private boolean isValid;

    public static WireTransferEntity of(Long id,
                              String requestId,
                              String fromMembershipId,
                              String toMembershipId,
                              String toBankName,
                              String toBankAccountNumber,
                              int wireTransferAmount,
                              boolean isValid) {
        return new WireTransferEntity(
                id,
                requestId,
                fromMembershipId,
                toMembershipId,
                toBankName,
                toBankAccountNumber,
                wireTransferAmount,
                isValid);
    }
}

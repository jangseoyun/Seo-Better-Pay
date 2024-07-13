package com.yun.payment.adapter.out.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_payment_result_receipt")
public class PaymentResultReceiptEntity {
    @Id
    @Column(name = "payment_result_receipt_id")
    private String paymentOrderId;
    @Column(name = "membership_id")
    private String membershipId;
    @Column(name = "memo")
    private String memo;
    @Column(name = "payment_amount")
    private int paymentAmount;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;
    @Column(name = "payment_type")
    private String paymentType;

    @Builder
    public PaymentResultReceiptEntity(String paymentOrderId,
                                      String membershipId,
                                      String memo,
                                      int paymentAmount,
                                      PaymentStatus paymentStatus,
                                      String paymentType) {
        this.paymentOrderId = paymentOrderId;
        this.membershipId = membershipId;
        this.memo = memo;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentResultReceiptEntity that)) return false;
        return Objects.equals(paymentOrderId, that.paymentOrderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentOrderId);
    }
}

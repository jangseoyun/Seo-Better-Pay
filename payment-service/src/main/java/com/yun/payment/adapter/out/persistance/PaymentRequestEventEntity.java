package com.yun.payment.adapter.out.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_payment_request_event")
public class PaymentRequestEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_request_event_id")
    private Long id;
    @Column(name = "payment_order_id")
    private String paymentOrderId;
    @Column(name = "membership_id")
    private String membershipId;
    @Column(name = "memo")
    private String memo;
    @Column(name = "request_amount")
    private int requestAmount;
    @Column(name = "payment_type")
    private String paymentType;
    //날짜

    @Builder
    public PaymentRequestEventEntity(String paymentOrderId, String membershipId, String memo, int requestAmount, String paymentType) {
        this.paymentOrderId = paymentOrderId;
        this.membershipId = membershipId;
        this.memo = memo;
        this.requestAmount = requestAmount;
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentRequestEventEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.yun.payment.adapter.out.persistance;

import com.yun.payment.domain.PaymentRequestInfo;
import com.yun.payment.domain.PaymentSettledSuccessResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentRequestEventEntity toEntity(PaymentRequestInfo domain) {
        return PaymentRequestEventEntity.builder()
                .paymentOrderId(domain.getRequestOrderId())
                .membershipId(domain.getMembershipId())
                .requestAmount(domain.getRequestAmount())
                .memo(domain.getMemo())
                .paymentType(domain.getPaymentType())
                .build();
    }

    public PaymentRequestInfo toPaymentRequestInfo(PaymentRequestEventEntity entity) {
        return PaymentRequestInfo.generatedPaymentRequestInfo(
                new PaymentRequestInfo.MembershipId(entity.getMembershipId()),
                new PaymentRequestInfo.RequestOrderId(entity.getPaymentOrderId()),
                new PaymentRequestInfo.Memo(entity.getMemo()),
                new PaymentRequestInfo.RequestAmount(entity.getRequestAmount()),
                new PaymentRequestInfo.PaymentType(entity.getPaymentType())
        );
    }

    //factory를 통해 데이터를 가공하고 mapper는 mapper의 역할만 하도록 수정
    public PaymentResultReceiptEntity toReceiptEntity(PaymentRequestInfo domain) {
        return PaymentResultReceiptEntity.builder()
                .paymentOrderId(domain.getRequestOrderId())
                .membershipId(domain.getMembershipId())
                .paymentAmount(domain.getRequestAmount())
                .memo(domain.getMemo())
                .paymentStatus(PaymentStatus.SUCCESS)
                .paymentType(domain.getPaymentType())
                .build();
    }

    public PaymentRequestInfo toPaymentRequestInfo(PaymentResultReceiptEntity entity) {
        return PaymentRequestInfo.generatedPaymentRequestInfo(
                new PaymentRequestInfo.MembershipId(entity.getMembershipId()),
                new PaymentRequestInfo.RequestOrderId(entity.getPaymentOrderId()),
                new PaymentRequestInfo.Memo(entity.getMemo()),
                new PaymentRequestInfo.RequestAmount(entity.getPaymentAmount()),
                new PaymentRequestInfo.PaymentType(entity.getPaymentType())
        );
    }
}

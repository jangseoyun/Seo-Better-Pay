package com.yun.payment.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.payment.adapter.in.web.model.MembershipPaymentRequest;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class PaymentRequestCommand extends SelfValidating<MembershipPaymentRequest> {

    private String membershipId;
    private String requestOrderId;
    private String memo;
    private int requestAmount;
    private String paymentType;

    @Builder
    public PaymentRequestCommand(String membershipId, String requestOrderId, String memo, int requestAmount, String paymentType) {
        this.membershipId = membershipId;
        this.requestOrderId = requestOrderId;
        this.memo = memo;
        this.requestAmount = requestAmount;
        this.paymentType = paymentType;
        this.validateSelf();
    }
}

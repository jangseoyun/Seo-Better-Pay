package com.yun.payment.adapter.in.web.model;

public record MembershipPaymentRequest(
        String membershipId,
        String memo,
        int requestAmount,
        String paymentType
) {

}

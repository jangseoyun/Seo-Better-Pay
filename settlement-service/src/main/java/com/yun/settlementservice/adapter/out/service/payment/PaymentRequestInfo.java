package com.yun.settlementservice.adapter.out.service.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
public class PaymentRequestInfo {

    private String membershipId;
    private String requestOrderId;
    private String memo;
    private int requestAmount;
    private String paymentType;

    public static PaymentRequestInfo generatedPaymentRequestInfo(
        MembershipId membershipId,
        RequestOrderId requestOrderId,
        Memo memo,
        RequestAmount requestAmount,
        PaymentType paymentType
    ) {
        return new PaymentRequestInfo(membershipId.membershipId,
                requestOrderId.requestOrderId,
                memo.memo,
                requestAmount.requestAmount,
                paymentType.paymentType);
    }

    @Value
    public static class MembershipId {
        String membershipId;

        public MembershipId(String value) {
            this.membershipId = value;
        }
    }

    @Value
    public static class RequestOrderId {
        String requestOrderId;

        public RequestOrderId(String value) {
            this.requestOrderId = value;
        }
    }

    @Value
    public static class Memo {
        String memo;

        public Memo(String value) {
            this.memo = value;
        }
    }

    @Value
    public static class RequestAmount {
        int requestAmount;

        public RequestAmount(int value) {
            this.requestAmount = value;
        }
    }

    @Value
    public static class PaymentType {
        String paymentType;

        public PaymentType(String value) {
            this.paymentType = value;
        }
    }
}

package com.yun.payment.domain;

import lombok.*;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
public class PaymentSuccessOrderIds {

    private List<String> paymentOrderIdList;

    public static PaymentSuccessOrderIds generatedPaymentSuccessOrderIds (
            PaymentOrderIdList paymentOrderIdList
    ) {
        return new PaymentSuccessOrderIds(paymentOrderIdList.paymentOrderIdList);
    }

    @Value
    public static class PaymentOrderIdList {
        List<String> paymentOrderIdList;

        public PaymentOrderIdList(List<String> value) {
            this.paymentOrderIdList = value;
        }
    }
}

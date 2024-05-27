package com.yun.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@ToString
@Getter
@AllArgsConstructor
public class PaymentSettledSuccessResponse {

    private String paymentSettledSuccess;
    public static PaymentSettledSuccessResponse generatedPaymentSettledSuccessResponse(
           PaymentSettledSuccess paymentSettledSuccess
    ) {
        return new PaymentSettledSuccessResponse(paymentSettledSuccess.paymentSettledSuccess);
    }

    @Value
    public static class PaymentSettledSuccess {
        String paymentSettledSuccess;

        public PaymentSettledSuccess(String value) {
            this.paymentSettledSuccess = value;
        }
    }
}

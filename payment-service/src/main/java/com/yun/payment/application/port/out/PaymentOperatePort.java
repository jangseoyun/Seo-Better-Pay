package com.yun.payment.application.port.out;

import com.yun.payment.domain.PaymentRequestInfo;
import com.yun.payment.domain.PaymentSettledSuccessResponse;

import java.util.List;

import static com.yun.payment.domain.PaymentSuccessOrderIds.PaymentOrderIdList;

public interface PaymentOperatePort {
    PaymentRequestInfo paymentEventRecord(PaymentRequestInfo paymentRequestInfo);
    PaymentRequestInfo exportRequestPayment(PaymentRequestInfo paymentRequestInfo);
    List<PaymentRequestInfo> paymentSuccessList();
    List<PaymentRequestInfo> paymentSuccessByMembershipId(PaymentRequestInfo.MembershipId membershipId);
    PaymentSettledSuccessResponse markPaymentAsSettled(PaymentOrderIdList paymentOrderIdList);
}

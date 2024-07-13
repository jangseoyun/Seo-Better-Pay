package com.yun.payment.application.port.in;

import com.yun.payment.domain.PaymentRequestInfo;
import com.yun.payment.domain.PaymentSettledSuccessResponse;

import java.util.List;

public interface PaymentCommandUseCase {
    PaymentRequestInfo requestPayment(PaymentRequestCommand command);
    List<PaymentRequestInfo> getSuccessPaymentList();
    List<PaymentRequestInfo> getSuccessPaymentsByMembershipId(MembershipIdCommand command);
    PaymentSettledSuccessResponse finalizePaymentSettlement(UpdatePaymentStatusToSettledCommand command);
}

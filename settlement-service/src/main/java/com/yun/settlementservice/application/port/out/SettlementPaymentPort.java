package com.yun.settlementservice.application.port.out;

import com.yun.settlementservice.adapter.out.service.payment.PaymentRequestInfo;

import java.util.List;

public interface SettlementPaymentPort {
    List<PaymentRequestInfo> sendPaymentSuccessByMembershipId(PaymentRequestInfo.MembershipId membershipId);
}

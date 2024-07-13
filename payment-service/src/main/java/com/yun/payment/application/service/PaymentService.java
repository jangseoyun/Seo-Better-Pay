package com.yun.payment.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.payment.application.port.in.MembershipIdCommand;
import com.yun.payment.application.port.in.PaymentCommandUseCase;
import com.yun.payment.application.port.in.PaymentRequestCommand;
import com.yun.payment.application.port.in.UpdatePaymentStatusToSettledCommand;
import com.yun.payment.application.port.out.PaymentOperatePort;
import com.yun.payment.application.factory.PaymentApplicationFactory;
import com.yun.payment.domain.PaymentRequestInfo;
import com.yun.payment.domain.PaymentSettledSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class PaymentService implements PaymentCommandUseCase {

    private final PaymentOperatePort paymentOperatePort;

    @Override
    public PaymentRequestInfo requestPayment(PaymentRequestCommand command) {
        //결제 요청 기록
        PaymentRequestInfo responsePaymentInfo
                = paymentOperatePort.paymentEventRecord(PaymentApplicationFactory.newInstancePaymentInfo(command));
        //외부 결제 요청
        return paymentOperatePort.exportRequestPayment(responsePaymentInfo);
    }

    @Override
    public List<PaymentRequestInfo> getSuccessPaymentList() {
        return paymentOperatePort.paymentSuccessList();
    }

    @Override
    public List<PaymentRequestInfo> getSuccessPaymentsByMembershipId(MembershipIdCommand command) {
        return paymentOperatePort.paymentSuccessByMembershipId(PaymentApplicationFactory.newInstanceMembershipId(command));
    }

    @Override
    public PaymentSettledSuccessResponse finalizePaymentSettlement(UpdatePaymentStatusToSettledCommand command) {
        return paymentOperatePort.markPaymentAsSettled(PaymentApplicationFactory.newInstancePaymentOrderIdList(command));
    }
}

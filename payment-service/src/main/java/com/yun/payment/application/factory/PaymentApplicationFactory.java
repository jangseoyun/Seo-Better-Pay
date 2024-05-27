package com.yun.payment.application.factory;

import com.yun.payment.application.port.in.MembershipIdCommand;
import com.yun.payment.application.port.in.PaymentRequestCommand;
import com.yun.payment.application.port.in.UpdatePaymentStatusToSettledCommand;
import com.yun.payment.domain.PaymentRequestInfo;
import com.yun.payment.domain.PaymentSettledSuccessResponse;

import static com.yun.payment.domain.PaymentSuccessOrderIds.PaymentOrderIdList;

public class PaymentApplicationFactory {
    public static PaymentRequestInfo newInstancePaymentInfo(PaymentRequestCommand command) {
        return PaymentRequestInfo.generatedPaymentRequestInfo(
                new PaymentRequestInfo.MembershipId(command.getMembershipId()),
                new PaymentRequestInfo.RequestOrderId(command.getRequestOrderId()),
                new PaymentRequestInfo.Memo(command.getMemo()),
                new PaymentRequestInfo.RequestAmount(command.getRequestAmount()),
                new PaymentRequestInfo.PaymentType(command.getPaymentType())
        );
    }

    public static PaymentOrderIdList newInstancePaymentOrderIdList(UpdatePaymentStatusToSettledCommand command) {
        return new PaymentOrderIdList(command.getPaymentOrderIdList());
    }

    public static PaymentSettledSuccessResponse newSuccessResponse() {
        return PaymentSettledSuccessResponse.generatedPaymentSettledSuccessResponse(
                new PaymentSettledSuccessResponse.PaymentSettledSuccess("update success")
        );
    }

    public static PaymentRequestInfo.MembershipId newInstanceMembershipId(MembershipIdCommand command) {
        return new PaymentRequestInfo.MembershipId(command.getMembershipId());
    }
}

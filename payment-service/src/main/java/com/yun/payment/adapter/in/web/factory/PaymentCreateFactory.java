package com.yun.payment.adapter.in.web.factory;

import com.yun.payment.adapter.in.web.model.MembershipIdRequest;
import com.yun.payment.adapter.in.web.model.MembershipPaymentRequest;
import com.yun.payment.adapter.in.web.model.UpdatePaymentStatusToSettledRequest;
import com.yun.payment.adapter.out.persistance.PaymentRequestEventEntity;
import com.yun.payment.application.port.in.MembershipIdCommand;
import com.yun.payment.application.port.in.PaymentRequestCommand;
import com.yun.payment.application.port.in.UpdatePaymentStatusToSettledCommand;

import java.util.UUID;

public class PaymentCreateFactory {
    public static PaymentRequestCommand newInstanceCommand(MembershipPaymentRequest request) {
        return PaymentRequestCommand.builder()
                .membershipId(request.membershipId())
                .requestOrderId(UUID.randomUUID().toString())
                .memo(request.memo())
                .requestAmount(request.requestAmount())
                .paymentType(request.paymentType())
                .build();
    }
    public static MembershipIdCommand newInstanceCommand(MembershipIdRequest request) {
        return new MembershipIdCommand(request.membershipId());
    }

    public static UpdatePaymentStatusToSettledCommand newInstanceCommand(UpdatePaymentStatusToSettledRequest request) {
        return new UpdatePaymentStatusToSettledCommand(request.paymentOrderIdList());
    }
}

package com.yun.payment.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.payment.adapter.in.web.model.UpdatePaymentStatusToSettledRequest;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class UpdatePaymentStatusToSettledCommand extends SelfValidating<UpdatePaymentStatusToSettledRequest> {

    private List<String> paymentOrderIdList;

    public UpdatePaymentStatusToSettledCommand(List<String> paymentOrderIdList) {
        this.paymentOrderIdList = paymentOrderIdList;
        this.validateSelf();
    }
}

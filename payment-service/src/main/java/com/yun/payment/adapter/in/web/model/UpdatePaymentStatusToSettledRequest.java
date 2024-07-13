package com.yun.payment.adapter.in.web.model;

import java.util.List;

public record UpdatePaymentStatusToSettledRequest(
        List<String> paymentOrderIdList
) {
}

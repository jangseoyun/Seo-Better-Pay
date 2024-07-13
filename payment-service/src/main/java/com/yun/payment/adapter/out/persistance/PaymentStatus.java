package com.yun.payment.adapter.out.persistance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum PaymentStatus {
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    SETTLED("SETTLED");

    private String value;
}

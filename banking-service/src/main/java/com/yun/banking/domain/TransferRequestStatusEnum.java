package com.yun.banking.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum TransferRequestStatusEnum {
    REQUEST,
    PENDING,
    SUCCESS,
    ROLLBACK,
    FAIL
}

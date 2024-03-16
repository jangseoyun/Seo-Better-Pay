package com.yun.wiretransferservice.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum WireTransferRequestStatus {
    PENDING_REQUEST,
    SUCCESS,
    FAIL
}

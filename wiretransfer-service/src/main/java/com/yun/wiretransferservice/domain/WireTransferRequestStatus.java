package com.yun.wiretransferservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public enum WireTransferRequestStatus {
    PENDING_REQUEST,
    SUCCESS,
    FAIL
}

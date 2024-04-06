package com.yun.banking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BankingServiceErrorCode {
    ENTITY_NOT_FOUND        ("BS0001", HttpStatus.NOT_FOUND, "요청 정보 데이터가 존재하지 않습니다.");

    private String errorCode;
    private HttpStatus status;
    private String message;
}

package com.yun.banking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BankingServiceErrorCode {
    INVALID_USER_ID_PASSWORD        ("UA0003", HttpStatus.FORBIDDEN, "아이디 또는 비밀번호가 일치하지 않습니다. 회원정보를 확인해주세요");

    private String errorCode;
    private HttpStatus status;
    private String message;
}

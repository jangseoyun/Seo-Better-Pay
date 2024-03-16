package com.yun.wiretransferservice.application.port.out.banking;

/**
 * 외부 은행의 정보
 */
public record BankingInfo(
        String bankName,
        String bankAccountNumber,
        boolean isValid
) {
}

package com.yun.wiretransferservice.application.port.out.money;

/**
 * 송금 서비스에서 필요한 머니의 정보
 */
public record MoneyInfo(
    String membershipId,
    int moneyTotalBalance
) {
}

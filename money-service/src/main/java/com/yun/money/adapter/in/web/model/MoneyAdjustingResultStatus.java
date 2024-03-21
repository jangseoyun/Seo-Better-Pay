package com.yun.money.adapter.in.web.model;

public enum MoneyChangingResultStatus {
    SUCCEEDED,
    PENDING,
    FAILED,
    FAILED_NOT_ENOUGH_MONEY, //잔액 부족
    FAILED_NOT_EXIST_MEMBERSHIP, //멤버십 없음
    FAILED_NOT_EXIST_MONEY_CHANGING_REQUEST //머니 변액 요청 없음
}

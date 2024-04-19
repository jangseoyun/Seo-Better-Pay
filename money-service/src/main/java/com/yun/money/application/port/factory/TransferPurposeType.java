package com.yun.money.application.port.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TransferPurposeType {
    WIRE_TRANSFER("TR", "고객이 다른 계좌에 송금을 요청하는 경우.", true, true),
    PAY_TO("ST", "고객이 재화 등을 구입하고, 대금을 결제하는 경우", true, true),
    RECHARGE("RC", "고객이 이용기관 Point 등의 충전을 요청하는 경우", true, false),
    AUTH("AU", "계좌소유인증을 위해 입금이체 하는 경우", false, true),
    WITHDRAW("WD", "고객이 이용기관이 제공하는 별도 서비스 가입을 위해 출금을 요청하는 경우", true, false),
    EXCHANGE("EX", "고객이 환전을 위한 자금의 출금을 요청하는 경우", true, false);

    private String code;
    private String description;
    private boolean depositIsPossible;
    private boolean withdrawIsPossible;
}

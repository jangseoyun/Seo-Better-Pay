package com.yun.money.application.port.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AgreementAccountType {
    BANK_ACCOUNT("N", "계좌"),
    MEMBER_ACCOUNT("C", "계정");

    private String type;
    private String name;
}

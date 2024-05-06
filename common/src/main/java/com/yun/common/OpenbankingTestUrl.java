package com.yun.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenbankingTestUrl {

    TRANSFER_WITHDRAW("https://openapi.openbanking.or.kr/v2.0/transfer/withdraw/acnt_num");

    private String url;
}

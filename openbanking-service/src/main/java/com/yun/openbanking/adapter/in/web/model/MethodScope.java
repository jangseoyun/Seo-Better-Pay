package com.yun.openbanking.adapter.in.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 다중 scope는 공백으로 구분
 */
@Getter
@ToString
@AllArgsConstructor
public enum MethodScope {
    LOGIN("login", 90, true),
    INQUIRY("inquiry", 90, true),
    TRANSFER("transfer", 90, true),
    CARDINFO("cardinfo", 90, true),
    FINTECHINFO("fintechinfo", 90, true),
    INSUINFO("insuinfo", 90, true),
    LOANINFO("loaninfo", 90, true),
    OOB("oob", 90, false),
    SA("sa", 90, false);

    private String scopeName;
    private Integer expiresIn;
    private boolean refreshAvailable;
}

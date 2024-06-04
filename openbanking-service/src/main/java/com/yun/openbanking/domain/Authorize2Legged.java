package com.yun.openbanking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Authorize2Legged extends MemberAuthorize {
    private String scope;
    private String grantType;

    public Authorize2Legged(String scope, String grantType) {
        this.scope = scope;
        this.grantType = grantType;
    }
}

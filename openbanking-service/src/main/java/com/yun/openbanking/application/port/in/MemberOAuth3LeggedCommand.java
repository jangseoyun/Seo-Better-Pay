package com.yun.openbanking.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.openbanking.adapter.in.web.model.MemberOAuth3LeggedRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class MemberOAuth3LeggedCommand extends SelfValidating<MemberOAuth3LeggedRequest> {
    private String code; //사용자 인증 응답 authorization_code 일치여부 확인
    private String redirectUrl; //사용자 인증시 요청한 redirectUrl 확인
    private String grantType;

    public MemberOAuth3LeggedCommand(String code, String redirectUrl, String grantType) {
        this.code = code;
        this.redirectUrl = redirectUrl;
        this.grantType = grantType;
    }

}

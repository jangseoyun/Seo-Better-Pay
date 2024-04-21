package com.yun.openbanking.adapter.in.factory;

import com.yun.openbanking.adapter.in.web.model.GrantType;
import com.yun.openbanking.adapter.in.web.model.MemberOAuth3LeggedRequest;
import com.yun.openbanking.application.port.in.MemberOAuth3LeggedCommand;
public class MemberOAuthFactory {
    public static MemberOAuth3LeggedCommand of(MemberOAuth3LeggedRequest request) {
        return new MemberOAuth3LeggedCommand(
                request.code(),
                request.redirectUrl(),
                GrantType.AUTHORIZATION_CODE.getName()
        );
    }
}

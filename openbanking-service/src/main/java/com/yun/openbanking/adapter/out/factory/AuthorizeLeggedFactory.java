package com.yun.openbanking.adapter.out.factory;

import com.yun.openbanking.application.port.in.MemberOAuth3LeggedCommand;
import com.yun.openbanking.domain.Authorize3Legged;

public class AuthorizeLeggedFactory {
    public static Authorize3Legged of(MemberOAuth3LeggedCommand command) {
        return new Authorize3Legged(command.getCode(), command.getRedirectUrl(), command.getGrantType());
    }
}

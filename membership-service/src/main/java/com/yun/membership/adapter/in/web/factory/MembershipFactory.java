package com.yun.membership.adapter.in.web.factory;

import com.yun.membership.adapter.in.web.model.request.JwtTokenValidationRequest;
import com.yun.membership.adapter.in.web.model.request.MembershipAddressRequest;
import com.yun.membership.adapter.in.web.model.request.MembershipRefreshTokenRequest;
import com.yun.membership.application.port.in.MembershipAddressCommand;
import com.yun.membership.application.port.in.MembershipRefreshTokenCommand;
import com.yun.membership.application.port.in.TokenValidationCommand;

public class MembershipFactory {
    public static MembershipAddressCommand newAddressCommand(MembershipAddressRequest request) {
        return new MembershipAddressCommand(request.addressKeyword());
    }

    public static MembershipRefreshTokenCommand newRefreshTokenCommand(MembershipRefreshTokenRequest request) {
        return new MembershipRefreshTokenCommand(request.membershipId(), request.refreshToken());
    }

    public static TokenValidationCommand newTokenValidationCommand(JwtTokenValidationRequest request) {
        return new TokenValidationCommand(request.jwtToken());
    }
}

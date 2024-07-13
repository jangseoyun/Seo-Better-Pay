package com.yun.membership.application.port.in;

import com.yun.membership.adapter.in.web.model.MembershipResult;

public interface LoginMembershipUseCase {
    MembershipResult requestLogin(LoginMembershipCommand command);
    MembershipResult requestRefreshToken(MembershipRefreshTokenCommand command);
    MembershipResult requestTokenValidation(TokenValidationCommand command);
}

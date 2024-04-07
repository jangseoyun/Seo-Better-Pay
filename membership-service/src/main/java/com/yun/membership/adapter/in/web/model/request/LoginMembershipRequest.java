package com.yun.membership.adapter.in.web.model.request;

import com.yun.membership.application.port.in.LoginMembershipCommand;

/**
 * membershipId를 통해 로그인이 가능하다
 */
public record LoginMembershipRequest(
        String membershipId,
        String membershipPw
) {
    public LoginMembershipCommand toCommand() {

        return LoginMembershipCommand.of(membershipId, membershipPw);
    }

}

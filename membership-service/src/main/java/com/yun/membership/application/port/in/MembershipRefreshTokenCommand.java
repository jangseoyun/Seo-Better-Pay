package com.yun.membership.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.membership.adapter.in.web.model.request.MembershipRefreshTokenRequest;
import lombok.Getter;

@Getter
public class MembershipRefreshTokenCommand extends SelfValidating<MembershipRefreshTokenRequest> {
    private String membershipId;
    private String refreshToken;

    public MembershipRefreshTokenCommand(String membershipId, String refreshToken) {
        this.membershipId = membershipId;
        this.refreshToken = refreshToken;
        this.validateSelf();
    }
}

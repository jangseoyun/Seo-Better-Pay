package com.yun.membership.adapter.in.web.model.request;

public record MembershipRefreshTokenRequest(
        String membershipId,
        String refreshToken
) {
}

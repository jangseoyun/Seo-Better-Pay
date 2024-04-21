package com.yun.openbanking.adapter.out.service;

import com.yun.openbanking.domain.Authorize3Legged;
import com.yun.openbanking.domain.MemberAuthorize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidAuthorizeMapper {
    private final MemberAuthorize memberAuthorize;
    public ValidOAuth3LeggedRequest from(Authorize3Legged authorize3Legged) {
        return new ValidOAuth3LeggedRequest(
                authorize3Legged.code(),
                memberAuthorize.getClientId(),
                memberAuthorize.getClientSecret(),
                "http://localhost:8080",
                authorize3Legged.grantType()
        );
    }
}

package com.yun.openbanking.adapter.in.web.model;

public record MemberOAuth2LeggedRequest(
        /*@Length(max = 40)
        @Value("${open-banking.test.client_id}")
        String clientId,
        @Value("${open-banking.test.client_secret}")
        @Length(max = 40)
        String clientSecret,*/
        String scope,
        String grantType
) {
}

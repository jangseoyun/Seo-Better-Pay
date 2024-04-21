package com.yun.openbanking.domain;

public record Authorize3Legged(
        String code, String redirectUrl, String grantType
) {
}

package com.yun.openbanking.config;

import com.yun.openbanking.domain.MemberAuthorize;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OpenbankingAuthorizeOperation {
    private final MemberAuthorize memberAuthorize;

    @PostConstruct
    public void init() {
        log.info("memberAuthorize init() ID: {}", memberAuthorize.getClientId());
        log.info("memberAuthorize init() SECRET: {}", memberAuthorize.getClientSecret());
    }
}

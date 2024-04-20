package com.yun.openbanking.config;

import com.yun.openbanking.domain.MemberAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class OpenbankingAuthorizePrepareConfig {

    @Value("${open-banking.test.client_id}")
    private String clientId;
    @Value("${open-banking.test.client_secret}")
    private String clientSecret;

    @Bean
    public MemberAuthorize startAuth() {
        log.info("memberAuthorize init() 실행");
        return new MemberAuthorize(clientId, clientSecret);
    }

}

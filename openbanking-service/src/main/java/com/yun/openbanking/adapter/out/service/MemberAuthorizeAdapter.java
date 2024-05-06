package com.yun.openbanking.adapter.out.service;

import com.yun.common.httpclient.CommonRestClient;
import com.yun.openbanking.application.port.out.MemberAuthorizePort;
import com.yun.openbanking.domain.Authorize3Legged;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberAuthorizeAdapter implements MemberAuthorizePort {

    private final CommonRestClient openBankingAuthorizeClient;
    private final ValidAuthorizeMapper mapper;

    @Value("${open-banking.test.url}")
    private String openBankingTestUrl;

    @Override
    public ResponseEntity requestMemberAuthorizeToken(Authorize3Legged authorize3Legged) {
        log.info("authorize3Legged : {}", authorize3Legged);
        String url = String.join("/",openBankingTestUrl, "oauth/2.0/token");
        log.info("openbanking test url : {}", url);
        ValidOAuth3LeggedRequest validOAuth3LeggedRequest = mapper.from(authorize3Legged);
        log.info("validOAuth3LeggedRequest: {}", validOAuth3LeggedRequest);

        ResponseEntity response = openBankingAuthorizeClient.sendPostRequest(url, validOAuth3LeggedRequest);
        log.info("adapter response: {}", response.getBody());
        return response;
    }
}

package com.yun.openbanking.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.httpclient.CommonRestClient;
import com.yun.openbanking.application.port.out.MemberAuthorizePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAuthorizeAdapter implements MemberAuthorizePort {

    private final CommonRestClient openBankingAuthorizeClient;
    private final ObjectMapper objectMapper;

    @Value("${open-banking.test.url}")
    private String openBankingTestUrl;

    @Override
    public void requestMemberAuthorize() {

    }
}

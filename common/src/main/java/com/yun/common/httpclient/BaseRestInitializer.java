package com.yun.common.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.stereotype.Component;

//TODO: header 설정
@Slf4j
@Component
public class BaseRestInitializer implements ClientHttpRequestInitializer {
    @Override
    public void initialize(ClientHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        //X-Frame-Options는 페이지가 다른 페이지의 프레임 내에서 로드되는 것을 방지하는 데 사용
        headers.add("X-Frame-Options", "DENY");
        //CSP는 웹 페이지에서 실행될 수 있는 리소스의 출처를 명시하는 정책
        headers.add("Content-Security-Policy", "default-src 'self'");
        //X-Content-Type-Options는 브라우저가 MIME 타입을 스니핑하지 못하도록 하는데 사용
        headers.add("X-Content-Type-Options", "nosniff");
    }
}

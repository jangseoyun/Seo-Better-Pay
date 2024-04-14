package com.yun.common.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class BaseRestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.debug("http request body: {}", body.toString());
        // 다음 인터셉터로 요청을 전달하고 응답을 받습니다.
        ClientHttpResponse response = execution.execute(request, body);
        // 응답을 수정하거나 추가 작업을 수행할 수도 있습니다.
        log.debug("http response body: {}", response.getBody());
        return response;
    }

}

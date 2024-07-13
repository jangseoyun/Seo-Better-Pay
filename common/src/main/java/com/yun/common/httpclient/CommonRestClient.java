package com.yun.common.httpclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommonRestClient {

    private final RestClient restClient;

    public String sendGetRequest(String url) {
        log.info("common url : {}", url);
        return restClient
                .get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    throw new RuntimeException("sendGetRequest exception: " + response.getBody());
                }))
                .body(String.class);
    }

    public <T> ResponseEntity sendPostRequest(String url, T body) {
        return restClient
                .post()
                .uri(url)
                .accept(APPLICATION_JSON)
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    throw new IOException(response.getBody().toString());
                }))
                .toEntity(String.class);
    }
}

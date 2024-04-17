package com.yun.common.httpclient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class CommonRestClient {

    private final RestClient restClient;

    public ResponseEntity sendGetRequest(String url) {
        return restClient
                .get()
                .uri(url)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    throw new RuntimeException("sendGetRequest exception");
                }))
                .toBodilessEntity();
    }

    public ResponseEntity sendPostRequest(String url, String body) {
        return restClient
                .post()
                .uri(url)
                .accept()
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    throw new IOException(response.getBody().toString());
                }))
                .toBodilessEntity();
    }
}

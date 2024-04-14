package com.yun.common.httpclient;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    @Bean
    public RestClient restClient() {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofMillis(5000));
        factory.setConnectionRequestTimeout(Duration.ofMillis(2000));

        return RestClient.builder()
                .requestFactory(factory)
                .messageConverters(converters -> converters.add(new JsonbHttpMessageConverter()))
                .requestInterceptor(new BaseRestInterceptor())
                .requestInitializer(new BaseRestInitializer())
                .build();
    }

}

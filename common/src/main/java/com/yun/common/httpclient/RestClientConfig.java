package com.yun.common.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
public class RestClientConfig {

    @Bean
    public RestClient httpConnectionConfig() {
        /*HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofMillis(5000));
        factory.setConnectionRequestTimeout(Duration.ofMillis(2000));

        return RestClient.builder()
                .requestFactory(factory)
                .messageConverters(converters -> converters.add(new JsonbHttpMessageConverter()))
                .baseUrl("")
                .requestInterceptor(new BaseRestInterceptor())
                .requestInitializer(new BaseRestInitializer())
                .build();*/
        return RestClient.create();
    }

}

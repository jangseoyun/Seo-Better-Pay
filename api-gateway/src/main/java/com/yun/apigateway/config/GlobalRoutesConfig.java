package com.yun.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.yun.apigateway.model.HeaderInfo.PRE_REQUEST_HEADER;

@Configuration
public class GlobalRoutesConfig {
    //인증이 필요한 로직은 api/v1 필요없는것은 바로 도메인 주소

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder.routes()
                .route("api-request", predicate -> predicate
                        .path("/api/v1/**")
                        .filters(filter -> filter
                                .addRequestHeader(PRE_REQUEST_HEADER.getName(), PRE_REQUEST_HEADER.getValue()))
                        .uri("http://pre-api-secure:8080")
                ).build();
    }
}

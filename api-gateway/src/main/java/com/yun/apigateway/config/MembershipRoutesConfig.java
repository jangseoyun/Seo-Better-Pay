package com.yun.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MembershipRoutesConfig {

    @Bean
    public RouteLocator membershipRoute(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder.routes()
                .route("membership-login-request", predicate -> predicate
                        .path("/api/v1/membership/register", "/api/v1/membership/login")
                        .uri("http://membership-service:8080")
                ).build();
    }

}

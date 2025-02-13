package com.yun.apigateway.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PreLoggingFilter implements GlobalFilter, Ordered {
    private static final Logger LOGGER = LogManager.getLogger(PreLoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest exchangeRequest = exchange.getRequest();
        LOGGER.info("exchangeRequest. 요청: {}, 요청 URL: {}", exchangeRequest, exchangeRequest.getURI().toString());
        ServerHttpResponse exchangeResponse = exchange.getResponse();
        LOGGER.info("exchangeResponse. 요청: {}", exchangeResponse);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

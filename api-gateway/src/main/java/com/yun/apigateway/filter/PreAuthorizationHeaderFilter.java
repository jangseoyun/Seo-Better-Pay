package com.yun.apigateway.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PreAuthorizationHeaderFilter extends AbstractGatewayFilterFactory<PreAuthorizationHeaderFilter.Config> implements Ordered {
    private static final Logger LOGGER = LogManager.getLogger(PreAuthorizationHeaderFilter.class);
    private final Environment env;

    public PreAuthorizationHeaderFilter(Environment env) {
        super(Config.class);
        this.env = env;
    }

    public static class Config {
        //configuration properties
    }

    @Override
    public GatewayFilter apply(Config config) {
        LOGGER.info("PreAuthorizationHeaderFilter apply");

        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "no authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeaderToken = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeaderToken.replace("Bearer", "");

            if (!isJwtValue(jwt)) {
                return onError(exchange, "JWT Token is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errorMessage, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        LOGGER.info(errorMessage);
        return response.setComplete();
    }

    private boolean isJwtValue(String jwt) {
        boolean returnValue = true;
        String subject = null;

        try {
            //subject의 값을 검증하는데, 이때 JWT 토큰 생성시 사용한 암호화 알고리즘 복호화를 한다
                /*Jwts.parser().setSigninKey(env.getProperty("token.secret"))
                        .parseClaimsJws(jwt).getBody()
                        .getSubject();*/
        } catch (Exception exception) {
            returnValue = false;
        }

        if (subject == null || subject.isEmpty()) {
            returnValue = false;
        }

        return returnValue;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

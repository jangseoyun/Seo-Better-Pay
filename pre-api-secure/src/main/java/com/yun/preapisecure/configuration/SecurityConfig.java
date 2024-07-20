package com.yun.preapisecure.configuration;

import com.yun.preapisecure.dsl.RestApiDsl;
import com.yun.preapisecure.handler.RestAccessDeniedHandler;
import com.yun.preapisecure.handler.RestAuthenticationEntryPoint;
import com.yun.preapisecure.handler.RestAuthenticationFailureHandler;
import com.yun.preapisecure.handler.RestAuthenticationSuccessHandler;
import com.yun.preapisecure.provider.RestAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    private final RestAuthenticationProvider restAuthenticationProvider;
    private final RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    private final RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Bean
    @Order(1)
    public SecurityFilterChain restFilterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder managerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder.authenticationProvider(restAuthenticationProvider);
        AuthenticationManager authenticationManager = managerBuilder.build();

        httpSecurity
                .securityMatchers(matcher ->
                        matcher.requestMatchers("/api/v1/**")
                )
                .authorizeHttpRequests(requestAuth -> requestAuth
                        .requestMatchers("/css/**", "/images/**", "/js/**", "/webjars/**", "/favicon.*", "/*/icon-*").permitAll()
                        .requestMatchers("/auth/v1/pre").permitAll()
                        //.requestMatchers("/api/v1/admin/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())
                .authenticationManager(authenticationManager)
                .with(new RestApiDsl<>(), restDsl -> restDsl
                        .restSuccessHandler(restAuthenticationSuccessHandler)
                        .restFailureHandler(restAuthenticationFailureHandler)
                        .loginProcessingUrl("/api/v1/membership/login")
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                        .accessDeniedHandler(new RestAccessDeniedHandler())
                );

        return httpSecurity.build();
    }

}

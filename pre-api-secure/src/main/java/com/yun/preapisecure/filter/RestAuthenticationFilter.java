package com.yun.preapisecure.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.preapisecure.domain.model.RequestAccessMembership;
import com.yun.preapisecure.provider.token.RestAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class RestAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String X_REQUEST_WITH = "X-Requested-With";
    private final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RestAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/v1/membership/login", "POST"));
    }

    public SecurityContextRepository getSecurityContextRepository(HttpSecurity httpSecurity) {
        SecurityContextRepository securityContextRepository = httpSecurity.getSharedObject(SecurityContextRepository.class);
        if (securityContextRepository == null) {
            securityContextRepository = new DelegatingSecurityContextRepository(
                    new RequestAttributeSecurityContextRepository(), new HttpSessionSecurityContextRepository()
            );
        }
        return securityContextRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.name().equals(request.getMethod()) || !isAjax(request)) {
            throw new IllegalArgumentException("Authentication method not supported");
        }

        RequestAccessMembership requestAccessMembership = objectMapper.readValue(request.getReader(), RequestAccessMembership.class);

        if (!StringUtils.hasText(requestAccessMembership.getMembershipId()) || !StringUtils.hasText(requestAccessMembership.getMembershipPw())) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }

        RestAuthenticationToken token = new RestAuthenticationToken(requestAccessMembership.getMembershipId(),requestAccessMembership.getMembershipPw());
        return getAuthenticationManager().authenticate(token);
    }

    private boolean isAjax(HttpServletRequest request) {
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUEST_WITH));
    }
}

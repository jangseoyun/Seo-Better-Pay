package com.yun.preapisecure.domain;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomAuthenticationDetails extends WebAuthenticationDetails {

    private final String secretKey;
    public CustomAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.secretKey = request.getHeader("secret_key");
    }

    public String getSecretKey() {
        return secretKey;
    }
}

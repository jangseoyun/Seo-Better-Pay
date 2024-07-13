package com.yun.membership.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.membership.adapter.in.web.model.request.JwtTokenValidationRequest;
import lombok.Getter;

@Getter
public class TokenValidationCommand extends SelfValidating<JwtTokenValidationRequest> {
    private String jwtToken;

    public TokenValidationCommand(String jwtToken) {
        this.jwtToken = jwtToken;
        this.validateSelf();
    }
}

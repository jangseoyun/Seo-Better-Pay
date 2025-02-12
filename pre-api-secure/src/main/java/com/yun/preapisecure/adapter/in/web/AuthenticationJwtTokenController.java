package com.yun.preapisecure.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1")
public class AuthenticationJwtTokenController {

    private final Environment environment;

    //발급받은 jwtToken을 통해 암호화하고 인증 토큰을 발급한다
    @GetMapping("/pre")
    public String createAuthenticationToken(HttpServletRequest request) {
        log.info("AuthenticationJwtTokenController request ServerPort: {}", request.getServerPort());
        log.info("AuthenticationJwtTokenController environment ServerPort: {}", environment.getProperty("local.server.port"));

        return "";
    }
}

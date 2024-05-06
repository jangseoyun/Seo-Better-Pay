package com.yun.openbanking.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.openbanking.adapter.in.factory.MemberOAuthFactory;
import com.yun.openbanking.adapter.in.web.model.MemberAuthorizeRequest;
import com.yun.openbanking.adapter.in.web.model.MemberOAuth3LeggedRequest;
import com.yun.openbanking.application.port.in.MemberAuthorizeUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/open-banking")
public class MemberAuthorizeController {

    private final MemberAuthorizeUseCase memberAuthorizeUseCase;

    /**
     * 사용자인증 code 발급 후 토큰 발급(3-legged)
     * @param request
     * @return
     */
    @GetMapping("/oauth/2.0/authorize")
    public ResponseEntity memberAuthorizeAccount(MemberOAuth3LeggedRequest request) {
        log.info("사용자 인증 API request: {}", request);
        ResponseEntity response = memberAuthorizeUseCase.request3AuthorizeMember(MemberOAuthFactory.of(request));
        log.info("controller response: {}", response.getBody());
        return response;
    }

}

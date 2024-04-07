package com.yun.openbanking.adapter.in.web.auth;

import com.yun.common.WebAdapter;
import com.yun.openbanking.adapter.in.web.model.MemberAuthorizeRequest;
import com.yun.openbanking.application.port.in.MemberAuthorizeUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/open-banking")
public class MemberAuthorizeController {

    private final MemberAuthorizeUseCase memberAuthorizeUseCase;

    //TODO: url enum 으로 분리
    @GetMapping("/oauth/2.0/authorize")
    public String memberAuthorizeAccount(@RequestParam MemberAuthorizeRequest request) {
        log.info("사용자 인증 API request: {}", request);
        memberAuthorizeUseCase.requestAuthorizeMember(request.toCommand());
        return "";
    }

}

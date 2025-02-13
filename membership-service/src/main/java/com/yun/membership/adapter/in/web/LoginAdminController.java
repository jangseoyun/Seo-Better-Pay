package com.yun.membership.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.membership.adapter.in.web.factory.MembershipFactory;
import com.yun.membership.adapter.in.web.model.MembershipResult;
import com.yun.membership.adapter.in.web.model.request.JwtTokenValidationRequest;
import com.yun.membership.adapter.in.web.model.request.LoginMembershipRequest;
import com.yun.membership.adapter.in.web.model.request.MembershipRefreshTokenRequest;
import com.yun.membership.application.port.in.LoginMembershipUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class LoginAdminController {

    private final LoginMembershipUseCase loginMembershipUseCase;

    @PostMapping("/login")
    public ResponseEntity<MembershipResult> membershipAccessLogin(@RequestBody @Valid LoginMembershipRequest request, HttpServletRequest httpRequest) {
        log.info("request = /membership/login | LoginMembershipRequest: {}", request);
        MembershipResult membershipResult = loginMembershipUseCase.requestLogin(request.toCommand());
        return ResponseEntity.ok().body(membershipResult);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<MembershipResult> membershipAccessRefreshToken(@RequestBody @Valid MembershipRefreshTokenRequest request) {
        log.info("request = /membership/refresh-token | MembershipRefreshTokenRequest: {}", request);
        MembershipResult membershipResult = loginMembershipUseCase.requestRefreshToken(MembershipFactory.newRefreshTokenCommand(request));
        return ResponseEntity.ok().body(membershipResult);
    }

    @PostMapping("/token-validate")
    public ResponseEntity<MembershipResult> tokenExpireCheck(@RequestBody @Valid JwtTokenValidationRequest request) {
        log.info("request = /membership/token-validate | JwtTokenValidationRequest: {}", request);
        MembershipResult membershipResult = loginMembershipUseCase.requestTokenValidation(MembershipFactory.newTokenValidationCommand(request));
        return ResponseEntity.ok().body(membershipResult);
    }

}

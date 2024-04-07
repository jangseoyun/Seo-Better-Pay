package com.yun.membership.adapter.in.web;

import com.yun.common.WebAdapter;
import com.yun.membership.adapter.in.web.model.MembershipResult;
import com.yun.membership.adapter.in.web.model.request.LoginMembershipRequest;
import com.yun.membership.application.port.in.LoginMembershipUseCase;
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
@RequestMapping("/membership")
public class LoginMembershipController {

    private final LoginMembershipUseCase loginMembershipUseCase;

    @PostMapping("/login")
    public ResponseEntity membershipAccessLogin(@RequestBody LoginMembershipRequest request) {
        log.info("request = /membership/login | LoginMembershipRequest: {}", request);
        MembershipResult membershipResult = loginMembershipUseCase.requestLogin(request.toCommand());
        return ResponseEntity.ok().body(membershipResult);
    }

}

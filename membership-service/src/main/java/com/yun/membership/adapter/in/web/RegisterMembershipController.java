package com.yun.membership.adapter.in.web;

import com.yun.common.WebAdapter;
import com.yun.membership.adapter.in.web.model.request.RegisterMembershipRequest;
import com.yun.membership.application.port.in.RegisterMembershipUseCase;
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
@RequestMapping(value = "/membership")
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping("/register")
    public ResponseEntity registerMemberShip(@RequestBody RegisterMembershipRequest registerMembershipRequest) {
        log.info("membership register request: {}", registerMembershipRequest);
        return ResponseEntity
                .ok()
                .body(registerMembershipUseCase.registerMembership(registerMembershipRequest.toCommand()));
    }
}

package com.yun.membership.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.membership.adapter.in.web.model.MembershipResult;
import com.yun.membership.adapter.in.web.model.request.RegisterMembershipRequest;
import com.yun.membership.application.port.in.RegisterMembershipUseCase;
import com.yun.membership.domain.Membership;
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
@RequestMapping(value = "/api/v1/membership")
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping("/register")
    public ResponseEntity<MembershipResult> registerMemberShip(@RequestBody @Valid RegisterMembershipRequest registerMembershipRequest) {
        log.info("membership register request: {}", registerMembershipRequest);
        Membership membership = registerMembershipUseCase.registerMembership(registerMembershipRequest.toCommand());
        return ResponseEntity.ok().body(MembershipResult.success(membership));
    }

}

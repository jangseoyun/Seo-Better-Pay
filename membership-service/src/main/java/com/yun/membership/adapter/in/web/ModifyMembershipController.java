package com.yun.membership.adapter.in.web;

import com.yun.common.WebAdapter;
import com.yun.membership.adapter.in.web.model.request.ModifyMembershipRequest;
import com.yun.membership.application.port.in.ModifyMembershipUseCase;
import com.yun.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/membership")
public class ModifyMembershipController {
    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PutMapping("/modify")
    public ResponseEntity modifyMembershipById(@RequestBody ModifyMembershipRequest modifyMembershipRequest) {
        Membership membership = modifyMembershipUseCase.modifyMembershipInfo(modifyMembershipRequest.toCommand());
        return ResponseEntity.ok().body(membership);
    }
}

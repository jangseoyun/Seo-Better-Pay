package com.yun.membership.adapter.in.web;

import com.yun.membership.adapter.in.web.model.request.ReadMembershipRequest;
import com.yun.membership.application.port.in.ReadMembershipUseCase;
import com.yun.membership.domain.Membership;
import common.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/membership")
public class ReadMembershipController {

    private final ReadMembershipUseCase readMembershipUseCase;

    @GetMapping("")
    public ResponseEntity findAllRegisterMember(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Membership> allMembershipUser = readMembershipUseCase.getAllMembershipUser(pageable);
        return ResponseEntity
                .ok()
                .body(allMembershipUser);
    }

    @GetMapping("/{membershipId}")
    public ResponseEntity findByMembershipId(@PathVariable("membershipId") ReadMembershipRequest request) {
        Membership membership = readMembershipUseCase.getMembershipsByMemberId(request.toCommand());
        return ResponseEntity
                .ok()
                .body(membership);
    }
}

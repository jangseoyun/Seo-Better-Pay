package com.yun.membership.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.membership.adapter.in.web.factory.MembershipFactory;
import com.yun.membership.adapter.in.web.model.MembershipResult;
import com.yun.membership.adapter.in.web.model.request.MembershipAddressRequest;
import com.yun.membership.adapter.in.web.model.request.ReadMembershipRequest;
import com.yun.membership.application.port.in.ReadMembershipUseCase;
import com.yun.membership.domain.Membership;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok().body(allMembershipUser);
    }

    @GetMapping("/{membershipId}")
    public ResponseEntity<MembershipResult> findByMembershipId(@PathVariable("membershipId") @Valid ReadMembershipRequest request) {
        Membership membership = readMembershipUseCase.getMembershipsByMemberId(request.toCommand());
        log.info("membership: {}", membership);
        return ResponseEntity.ok().body(MembershipResult.success(membership));
    }

    @GetMapping("/address")
    public ResponseEntity<MembershipResult> findByMembershipIdByAddress(@RequestParam("addressKeyword") MembershipAddressRequest address) {
        log.info("membership request param: {}", address.addressKeyword().toString());
        List<Membership> membershipsByAddress = readMembershipUseCase.getMembershipsByAddress(MembershipFactory.newAddressCommand(address));
        log.info("membership list: {}", membershipsByAddress);
        return ResponseEntity.ok().body(MembershipResult.success(membershipsByAddress));
    }
}

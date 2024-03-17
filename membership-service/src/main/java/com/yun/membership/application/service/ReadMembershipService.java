package com.yun.membership.application.service;

import com.yun.membership.application.port.in.ReadMembershipCommand;
import com.yun.membership.application.port.in.ReadMembershipUseCase;
import com.yun.membership.application.port.out.ReadMembershipPort;
import com.yun.membership.domain.Membership;
import com.yun.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadMembershipService implements ReadMembershipUseCase {

    private final ReadMembershipPort readMembershipPort;

    @Override
    public Page<Membership> getAllMembershipUser(Pageable pageable) {
        return readMembershipPort.findAllMembership(pageable);
    }

    @Override
    public Membership getMembershipsByMemberId(ReadMembershipCommand membershipIdCommand) {
        return readMembershipPort.findByMembershipId(membershipIdCommand.getMembershipId());
    }
}

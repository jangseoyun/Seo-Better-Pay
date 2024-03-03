package com.yun.membership.application.service;

import com.yun.membership.adapter.out.persistence.MembershipEntity;
import com.yun.membership.adapter.out.persistence.MembershipMapper;
import com.yun.membership.application.port.in.ReadMembershipCommand;
import com.yun.membership.application.port.in.ReadMembershipUseCase;
import com.yun.membership.application.port.out.ReadMembershipPort;
import com.yun.membership.domain.Membership;
import common.UseCase;
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
    private final MembershipMapper mapper;
    @Override
    public Page<Membership> getAllMembershipUser(Pageable pageable) {
        return readMembershipPort.findAllMembership(pageable)
                .map(membershipEntity -> mapper.mapToDomainEntity(membershipEntity));
    }

    @Override
    public Membership getMembershipsByMemberId(ReadMembershipCommand membershipIdCommand) {
        MembershipEntity byMembershipId = readMembershipPort.findByMembershipId(membershipIdCommand.toMembershipId());
        return mapper.mapToDomainEntity(byMembershipId);
    }
}

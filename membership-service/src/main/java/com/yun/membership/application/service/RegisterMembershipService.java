package com.yun.membership.application.service;

import com.yun.membership.adapter.out.persistence.MembershipEntity;
import com.yun.membership.adapter.out.persistence.MembershipMapper;
import com.yun.membership.application.port.in.RegisterMembershipCommand;
import com.yun.membership.application.port.in.RegisterMembershipUseCase;
import com.yun.membership.application.port.out.RegisterMembershipPort;
import com.yun.membership.domain.Membership;
import common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper mapper;
    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        //command -> DB
        MembershipEntity membershipEntity = registerMembershipPort.createdMembership(command.toMembership());
        //entity -> membership
        return mapper.mapToDomainEntity(membershipEntity);
    }
}

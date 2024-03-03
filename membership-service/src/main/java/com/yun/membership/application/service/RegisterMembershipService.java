package com.yun.membership.application.service;

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
    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        return registerMembershipPort.createdMembership(command.toMembership());
    }
}

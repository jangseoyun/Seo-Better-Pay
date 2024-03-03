package com.yun.membership.application.service;

import com.yun.membership.application.port.in.ModifyMembershipCommand;
import com.yun.membership.application.port.in.ModifyMembershipUseCase;
import com.yun.membership.application.port.out.ModifyMembershipPort;
import com.yun.membership.domain.Membership;
import common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;
    @Override
    public Membership modifyMembershipInfo(ModifyMembershipCommand command) {
        return modifyMembershipPort.updateMembershipInfo(command.toMembership());
    }
}

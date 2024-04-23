package com.yun.membership.application.service;

import com.yun.membership.application.port.in.ModifyMembershipCommand;
import com.yun.membership.application.port.in.ModifyMembershipUseCase;
import com.yun.membership.application.port.out.ModifyMembershipPort;
import com.yun.membership.application.port.out.ReadMembershipPort;
import com.yun.membership.domain.Membership;
import com.yun.common.anotation.UseCase;
import com.yun.membership.exception.MembershipModuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import static com.yun.membership.exception.MembershipErrorCode.*;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;
    //TODO: readMembership의 경우 캐싱을 통해 반복적인 DB 접근을 줄이며, 시큐리티를 통해 앞단에서 검증 하도록 개선 필요.
    private final ReadMembershipPort readMembershipPort;
    @Override
    public Membership modifyMembershipInfo(ModifyMembershipCommand command) {
        if (!readMembershipPort.findByMembershipId(command.getEditMembershipId()).isValid()) {
            throw new MembershipModuleException(USER_NOTFOUND_ACCOUNT, USER_NOTFOUND_ACCOUNT.getMessage());
        }
        return modifyMembershipPort.updateMembershipInfo(command.toMembership());
    }
}

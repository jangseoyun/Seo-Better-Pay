package com.yun.membership.application.service;

import com.yun.common.UseCase;
import com.yun.membership.adapter.in.web.model.MembershipResult;
import com.yun.membership.application.port.in.LoginMembershipCommand;
import com.yun.membership.application.port.in.LoginMembershipUseCase;
import com.yun.membership.application.port.out.ReadMembershipPort;
import com.yun.membership.domain.LoginInfoValidator;
import com.yun.membership.domain.Membership;
import com.yun.membership.exception.MembershipModuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import static com.yun.membership.exception.MembershipErrorCode.INVALID_USER_ID_PASSWORD;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginMembershipService implements LoginMembershipUseCase {

    private final ReadMembershipPort readMembershipPort;
    private final LoginInfoValidator loginInfoValidator;

    @Override
    public MembershipResult requestLogin(LoginMembershipCommand command) {

        Membership membership = readMembershipPort.findByMembershipId(command.getMembershipId());
        log.info("getMembership: {}", membership);

        boolean checkedMembershipPw = loginInfoValidator.checkMembershipPw(
                membership.getMembershipPw(), command.getMembershipPw()
        );

        if (!checkedMembershipPw) {
            throw new MembershipModuleException(INVALID_USER_ID_PASSWORD, INVALID_USER_ID_PASSWORD.getMessage());
        }

        return MembershipResult.success(membership.getMembershipId());
    }

}

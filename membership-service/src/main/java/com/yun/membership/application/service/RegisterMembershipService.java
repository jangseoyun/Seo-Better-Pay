package com.yun.membership.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.membership.application.port.in.RegisterMembershipCommand;
import com.yun.membership.application.port.in.RegisterMembershipUseCase;
import com.yun.membership.application.port.out.ReadMembershipPort;
import com.yun.membership.application.port.out.RegisterMembershipPort;
import com.yun.membership.application.port.out.axon.AxonRegisterMembershipPort;
import com.yun.membership.domain.Membership;
import com.yun.membership.exception.MembershipModuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import static com.yun.membership.exception.MembershipErrorCode.USER_ALREADY_EXIST_ACCOUNT;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;
    private final ReadMembershipPort readMembershipPort;
    private final AxonRegisterMembershipPort axonRegisterMembershipPort;

    @Cacheable(value = "membershipCache", key = "#command.membershipId")
    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {

        try {
            //존재하면 예외처리 : USER_ALREADY_EXIST_ACCOUNT
            readMembershipPort.findByMembershipId(command.toMembership().getMembershipId());
        } catch (MembershipModuleException findMembershipException) {
            //존재하지 않으면 저장
            log.info("findMembershipException : {} | {} : 회원 가입 가능: ", findMembershipException, command.toMembership().getMembershipId());
            //이벤트 처리
            Membership membership = registerMembershipPort.createdMembership(command.toMembership());
            axonRegisterMembershipPort.send(command);
            return membership;
        }

        throw new MembershipModuleException(USER_ALREADY_EXIST_ACCOUNT, USER_ALREADY_EXIST_ACCOUNT.getMessage());
    }
}

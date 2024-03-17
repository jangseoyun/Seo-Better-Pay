package com.yun.membership.application.port.in;

import com.yun.membership.domain.Membership;

public interface RegisterMembershipUseCase {
    /**
     * 회원가입
     * 존재하지 않는 회원: 데이터 검증 후 회원가입 진행
     * 존재하는 회원: 예외처리 MembershipErrorCode
     */
    Membership registerMembership(RegisterMembershipCommand command);
}

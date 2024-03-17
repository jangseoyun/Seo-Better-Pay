package com.yun.membership.adapter.in.web.model.request;

import com.yun.membership.application.port.in.ModifyMembershipCommand;

/**
 * 회원 정보 수정
 * email, password 수정의 경우 본인 인증 및 검증이 필요하므로 API 분리
 */
public record ModifyMembershipRequest(
        String membershipId,
        String address
) {
    public ModifyMembershipCommand toCommand() {
        return ModifyMembershipCommand.of(
                membershipId,
                address
        );
    }
}

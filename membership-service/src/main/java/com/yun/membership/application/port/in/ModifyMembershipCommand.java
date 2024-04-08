package com.yun.membership.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.membership.adapter.in.web.model.request.ModifyMembershipRequest;
import com.yun.membership.domain.ModifyMembership;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.yun.membership.domain.ModifyMembership.*;

/**
 * 회원 정보 수정
 * email, password 수정의 경우 본인 인증 및 검증이 필요하므로 API 분리
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipRequest> {

    private final String editMembershipId;
    private final String membershipPw;
    private final String address;

    private ModifyMembershipCommand(String editMembershipId, String membershipPw, String address) {
        this.editMembershipId = editMembershipId;
        this.membershipPw = membershipPw;
        this.address = address;
    }

    public static ModifyMembershipCommand of(String membershipId, String membershipPw, String address) {
        return new ModifyMembershipCommand(membershipId, membershipPw, address);
    }

    public ModifyMembership toMembership() {
        return generateInModifyMember(
                new EditMembershipId(editMembershipId),
                new MembershipPw(membershipPw),
                new MembershipAddress(address));
    }
}

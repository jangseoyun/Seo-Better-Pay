package com.yun.membership.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.membership.adapter.in.web.model.request.RegisterMembershipRequest;
import com.yun.membership.domain.Membership;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;

/**
 * RegisterMembershipCommand에 isValid : 회원의 활성화(권한) 여부
 * 최초 가입시 기본값 = false -> DB 저장 이후 true 변경
 * 내부 시스템 admin에서 권한을 변경할 수 있다
 */
@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipRequest> {

    private final String membershipId;
    private final String membershipPw;
    private final String membershipEmail;
    private final String name;
    private final String address;
    private final boolean isValid;

    private RegisterMembershipCommand(String membershipId,
                                     String membershipPw,
                                     String membershipEmail,
                                     String name,
                                     String address,
                                     boolean isValid) {
        this.membershipId = membershipId;
        this.membershipPw = membershipPw;
        this.membershipEmail = membershipEmail;
        this.name = name;
        this.address = address;
        this.isValid = isValid;
        this.validateSelf();
    }

    public static RegisterMembershipCommand of(String membershipId,
                                     String membershipPw,
                                     String membershipEmail,
                                     String name,
                                     String address,
                                     boolean isValid) {
        return new RegisterMembershipCommand(membershipId, membershipPw, membershipEmail, name, address, isValid);
    }

    public Membership toMembership() {
        return Membership.generateInMember(
                new Membership.MembershipId(membershipId),
                new Membership.MembershipPw(membershipPw),
                new Membership.MembershipName(name),
                new Membership.MembershipEmail(membershipEmail),
                new Membership.MembershipAddress(address),
                new Membership.MembershipIsValid(isValid)
        );
    }

}

package com.yun.membership.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.membership.domain.Membership;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {
    @NotNull
    @NotBlank
    private final String membershipId;

    @NotNull
    @NotBlank
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private final String membershipPw;

    @Email
    @NotBlank
    private final String membershipEmail;

    @NotNull
    @NotBlank
    private final String name;

    @NotNull
    @NotBlank
    private final String address;

    @AssertFalse
    private final boolean isValid; //내부 시스템 admin에서 권한을 변경할 수 있다

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

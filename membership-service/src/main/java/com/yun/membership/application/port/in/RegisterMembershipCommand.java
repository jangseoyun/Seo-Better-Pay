package com.yun.membership.application.port.in;

import com.yun.membership.domain.Membership;
import common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {
    @NotNull
    @NotBlank
    private final String name;
    @NotNull
    @NotBlank
    private final String email;
    @NotNull
    @NotBlank
    private final String address;
    @AssertTrue
    private final boolean isValid; //내부 시스템 admin에서 권한을 변경할 수 있다
    private final boolean isCorp;

    private RegisterMembershipCommand(String name, String email, String address, boolean isValid, boolean isCorp) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorp = isCorp;
    }

    public static RegisterMembershipCommand of(String name, String email, String address, boolean isValid, boolean isCorp) {
        return new RegisterMembershipCommand(name, email, address, isValid, isCorp);
    }

    public Membership toMembership() {
        return Membership.generateMember(
                new Membership.MembershipId(null),
                new Membership.MembershipName(name),
                new Membership.MembershipEmail(email),
                new Membership.MembershipAddress(address),
                new Membership.MembershipIsValid(isValid),
                new Membership.MembershipIsCorp(isCorp)
        );
    }

}

package com.yun.membership.application.port.in;

import com.yun.membership.adapter.in.web.model.request.ModifyMembershipRequest;
import com.yun.membership.domain.Membership;
import com.yun.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipRequest> {
    @NotNull
    @NotEmpty
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;
    private final boolean isCorp;

    private ModifyMembershipCommand(String membershipId, String name, String email, String address, boolean isCorp) {
        this.membershipId = membershipId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.isCorp = isCorp;
    }

    public static ModifyMembershipCommand of(String membershipId, String name, String email, String address, boolean isCorp) {
        return new ModifyMembershipCommand(membershipId, name, email, address, isCorp);
    }

    public Membership toMembership() {
        return Membership.generateMember(
                new Membership.MembershipId(membershipId),
                new Membership.MembershipName(name),
                new Membership.MembershipEmail(email),
                new Membership.MembershipAddress(address),
                new Membership.MembershipIsValid(true),
                new Membership.MembershipIsCorp(isCorp)
        );
    }
}

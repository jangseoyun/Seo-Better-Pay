package com.yun.membership.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.membership.adapter.in.web.model.request.ModifyMembershipRequest;
import com.yun.membership.domain.ModifyMembership;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.yun.membership.domain.ModifyMembership.*;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipRequest> {
    @NotNull
    @NotEmpty
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;

    private ModifyMembershipCommand(String membershipId, String name, String email, String address) {
        this.membershipId = membershipId;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public static ModifyMembershipCommand of(String membershipId, String name, String email, String address) {
        return new ModifyMembershipCommand(membershipId, name, email, address);
    }

    public ModifyMembership toMembership() {
        return generateInMember(
                new MembershipId(membershipId),
                new MembershipAddress(address),
                new MembershipEmail(email));
    }
}

package com.yun.membership.application.port.in;

import com.yun.membership.adapter.in.web.model.request.ReadMembershipRequest;
import com.yun.membership.domain.Membership;
import com.yun.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ReadMembershipCommand extends SelfValidating<ReadMembershipRequest> {

    private final String membershipId;

    private ReadMembershipCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }

    public static ReadMembershipCommand of(String membershipId) {
        return new ReadMembershipCommand(membershipId);
    }
}

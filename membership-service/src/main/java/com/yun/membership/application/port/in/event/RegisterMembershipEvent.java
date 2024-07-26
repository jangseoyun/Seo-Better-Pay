package com.yun.membership.application.port.in.event;

import com.yun.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipEvent extends SelfValidating<RegisterMembershipEvent> {
    private final String membershipId;
    private final String membershipPw;
    private final String membershipEmail;

    public RegisterMembershipEvent(String membershipId, String membershipPw, String membershipEmail) {
        this.membershipId = membershipId;
        this.membershipPw = membershipPw;
        this.membershipEmail = membershipEmail;
        this.validateSelf();
    }
}

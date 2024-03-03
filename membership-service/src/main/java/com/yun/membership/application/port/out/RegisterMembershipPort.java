package com.yun.membership.application.port.out;

import com.yun.membership.domain.Membership;

public interface RegisterMembershipPort {
    Membership createdMembership(Membership membership);
}

package com.yun.membership.application.port.out;

import com.yun.membership.adapter.out.persistence.MembershipEntity;
import com.yun.membership.domain.Membership;

public interface RegisterMembershipPort {
    MembershipEntity createdMembership(
            Membership membership
    );

}

package com.yun.membership.application.port.out;

import com.yun.membership.domain.Membership;

public interface ModifyMembershipPort {
    Membership updateMembershipInfo(Membership membership);
}

package com.yun.membership.application.port.out;

import com.yun.membership.domain.Membership;
import com.yun.membership.domain.ModifyMembership;

public interface ModifyMembershipPort {
    Membership updateMembershipInfo(ModifyMembership modifyMembership);
}

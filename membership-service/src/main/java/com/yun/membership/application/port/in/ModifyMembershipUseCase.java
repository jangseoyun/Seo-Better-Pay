package com.yun.membership.application.port.in;

import com.yun.membership.domain.Membership;

public interface ModifyMembershipUseCase {
    Membership modifyMembershipInfo(ModifyMembershipCommand command);
}

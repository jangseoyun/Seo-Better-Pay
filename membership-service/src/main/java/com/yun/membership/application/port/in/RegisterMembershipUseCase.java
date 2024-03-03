package com.yun.membership.application.port.in;

import com.yun.membership.domain.Membership;
import common.UseCase;

public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}

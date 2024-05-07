package com.yun.membership.application.port.out;

import com.yun.membership.domain.Membership;
import com.yun.membership.domain.ModifyMembership;
import com.yun.membership.jwt.JwtToken;

public interface ModifyMembershipPort {
    Membership updateMembershipInfo(ModifyMembership modifyMembership);
    void insertRefreshToken(JwtToken jwtToken);
}

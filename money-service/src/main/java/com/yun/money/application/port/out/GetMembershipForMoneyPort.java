package com.yun.money.application.port.out;

import com.yun.money.adapter.out.service.membership.MembershipServiceStatus;

public interface GetMembershipForMoneyPort {
    public MembershipServiceStatus getMembership(String membershipId);
}

package com.yun.money.application.port.out;

import com.yun.money.adapter.out.service.membership.MembershipServiceStatus;

import java.util.List;

public interface GetMembershipForMoneyPort {
    MembershipServiceStatus getMembership(String membershipId);
    List<String> getMembershipByAddress(String addressKeyword);
}

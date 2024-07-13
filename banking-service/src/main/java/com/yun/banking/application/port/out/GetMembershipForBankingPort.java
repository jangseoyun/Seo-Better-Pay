package com.yun.banking.application.port.out;

public interface GetMembershipForBankingPort {
    MembershipServiceStatus getMembership(String membershipId);
}

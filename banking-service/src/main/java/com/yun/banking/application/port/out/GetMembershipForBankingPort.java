package com.yun.banking.application.port.out;

public interface GetMembershipForBankingPort {
    public MembershipServiceStatus getMembership(String membershipId);
}

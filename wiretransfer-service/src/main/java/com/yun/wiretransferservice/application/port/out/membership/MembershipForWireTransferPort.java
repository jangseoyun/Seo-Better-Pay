package com.yun.wiretransferservice.application.port.out.membership;

public interface MembershipForWireTransferPort {
    MembershipStatus getMembershipStatus(String membershipId);
}

package com.yun.membership.application.port.out;

import com.yun.membership.domain.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReadMembershipPort {
    Membership findByMembershipId(String membershipId);
    Page<Membership> findAllMembership(Pageable pageable);
    List<Membership> findAllMembershipAddress(String address);
}

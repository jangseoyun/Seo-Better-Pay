package com.yun.membership.application.port.out;

import com.yun.membership.adapter.out.persistence.MembershipEntity;
import com.yun.membership.domain.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReadMembershipPort {
    MembershipEntity findByMembershipId(Membership.MembershipId membershipId);
    Page<MembershipEntity> findAllMembership(Pageable pageable);
}

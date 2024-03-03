package com.yun.membership.application.port.in;

import com.yun.membership.domain.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReadMembershipUseCase {
    Page<Membership> getAllMembershipUser(Pageable pageable);
    Membership getMembershipsByMemberId(ReadMembershipCommand readMembershipCommand);
}

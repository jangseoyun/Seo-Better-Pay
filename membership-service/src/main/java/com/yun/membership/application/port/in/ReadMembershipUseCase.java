package com.yun.membership.application.port.in;

import com.yun.membership.adapter.in.web.model.request.MembershipAddressRequest;
import com.yun.membership.domain.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReadMembershipUseCase {
    Page<Membership> getAllMembershipUser(Pageable pageable);
    Membership getMembershipsByMemberId(ReadMembershipCommand readMembershipCommand);
    List<Membership> getMembershipsByAddress(MembershipAddressCommand membershipAddressCommand);
}

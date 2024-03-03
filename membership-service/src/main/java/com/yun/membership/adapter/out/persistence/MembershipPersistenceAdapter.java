package com.yun.membership.adapter.out.persistence;

import com.yun.membership.application.port.out.RegisterMembershipPort;
import com.yun.membership.domain.Membership;
import common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort {
    private final MembershipJpaRepository membershipJpaRepository;
    @Override
    public MembershipEntity createdMembership(Membership membership) {
        return membershipJpaRepository.save(MembershipEntity.of(
                membership.getName(),
                membership.getAddress(),
                membership.getEmail(),
                membership.isValid(),
                membership.isCorp()
        ));
    }
}

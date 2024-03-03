package com.yun.membership.adapter.out.persistence;

import com.yun.membership.application.port.out.ReadMembershipPort;
import com.yun.membership.application.port.out.RegisterMembershipPort;
import com.yun.membership.domain.Membership;
import common.PersistenceAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, ReadMembershipPort {
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

    @Override
    public MembershipEntity findByMembershipId(Membership.MembershipId membershipId) {
        return membershipJpaRepository.findById(Long.valueOf(membershipId.getId()))
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("회원을 찾을 수 없습니다");
                });
    }

    @Override
    public Page<MembershipEntity> findAllMembership(Pageable pageable) {
        return membershipJpaRepository.findAll(pageable);
    }
}

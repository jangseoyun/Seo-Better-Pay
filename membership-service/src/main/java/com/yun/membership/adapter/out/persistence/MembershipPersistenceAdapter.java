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
    private final MembershipMapper mapper;
    @Override
    public Membership createdMembership(Membership membership) {
        MembershipEntity membershipEntity = membershipJpaRepository.save(MembershipEntity.of(
                membership.getName(),
                membership.getAddress(),
                membership.getEmail(),
                membership.isValid(),
                membership.isCorp()
        ));
        return mapper.mapToDomainEntity(membershipEntity);
    }

    @Override
    public Membership findByMembershipId(Membership.MembershipId membershipId) {
        MembershipEntity byMembershipId = membershipJpaRepository.findById(Long.valueOf(membershipId.getId()))
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("회원을 찾을 수 없습니다");
                });
        return mapper.mapToDomainEntity(byMembershipId);
    }

    @Override
    public Page<Membership> findAllMembership(Pageable pageable) {
        return membershipJpaRepository.findAll(pageable)
                .map(membershipEntity -> mapper.mapToDomainEntity(membershipEntity));
    }
}

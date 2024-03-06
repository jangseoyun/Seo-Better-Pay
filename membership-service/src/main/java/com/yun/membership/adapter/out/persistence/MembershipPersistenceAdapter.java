package com.yun.membership.adapter.out.persistence;

import com.yun.membership.application.port.out.ModifyMembershipPort;
import com.yun.membership.application.port.out.ReadMembershipPort;
import com.yun.membership.application.port.out.RegisterMembershipPort;
import com.yun.membership.domain.Membership;
import com.yun.common.PersistenceAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, ReadMembershipPort, ModifyMembershipPort {
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

    @Override
    public Membership updateMembershipInfo(Membership membership) {
        MembershipEntity getMembership = membershipJpaRepository.findById(Long.parseLong(membership.getMembershipId()))
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("회원 정보를 찾을 수 없습니다");
                });
        //TODO: email 수정시에는 해당 회원의 이메일 인증이 필요하다
        getMembership.updateMembershipInfo(membership.getName(), membership.getAddress());
        return mapper.mapToDomainEntity(membershipJpaRepository.save(getMembership));
    }
}

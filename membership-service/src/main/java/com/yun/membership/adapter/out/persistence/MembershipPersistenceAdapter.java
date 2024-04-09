package com.yun.membership.adapter.out.persistence;

import com.yun.common.PersistenceAdapter;
import com.yun.membership.application.port.out.ModifyMembershipPort;
import com.yun.membership.application.port.out.ReadMembershipPort;
import com.yun.membership.application.port.out.RegisterMembershipPort;
import com.yun.membership.domain.Membership;
import com.yun.membership.domain.ModifyMembership;
import com.yun.membership.exception.MembershipModuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.yun.membership.exception.MembershipErrorCode.USER_NOTFOUND_ACCOUNT;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, ReadMembershipPort, ModifyMembershipPort {

    private final MembershipJpaRepository membershipJpaRepository;
    private final MembershipMapper mapper;
    @Override
    public Membership createdMembership(Membership membership) {
        MembershipEntity membershipEntity = membershipJpaRepository.save(mapper.mapToEntity(membership));
        return mapper.mapToMembership(membershipEntity);
    }

    @Override
    public Membership findByMembershipId(String membershipId) {
        MembershipEntity membershipEntity = membershipJpaRepository.findById(membershipId)
                .orElseThrow(() -> {
                    throw new MembershipModuleException(USER_NOTFOUND_ACCOUNT, USER_NOTFOUND_ACCOUNT.getMessage());
                });
        return mapper.mapToMembership(membershipEntity);
    }

    @Override
    public Page<Membership> findAllMembership(Pageable pageable) {
        return membershipJpaRepository.findAll(pageable)
                .map(membershipEntity -> mapper.mapToMembership(membershipEntity));
    }

    @Override
    public Membership updateMembershipInfo(ModifyMembership modifyMembership) {
        MembershipEntity getMembership = membershipJpaRepository.findById(modifyMembership.getEditMembershipId())
                .orElseThrow(() -> {
                    throw new MembershipModuleException(USER_NOTFOUND_ACCOUNT, USER_NOTFOUND_ACCOUNT.getMessage());
                });

        getMembership.updateMembershipInfo(modifyMembership.getEditMembershipId(), modifyMembership.getAddress());
        return mapper.mapToMembership(membershipJpaRepository.save(getMembership));
    }
}

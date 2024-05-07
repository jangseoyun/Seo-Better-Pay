package com.yun.membership.adapter.out.persistence;

import com.yun.common.anotation.PersistenceAdapter;
import com.yun.membership.application.port.out.ModifyMembershipPort;
import com.yun.membership.application.port.out.ReadMembershipPort;
import com.yun.membership.application.port.out.RegisterMembershipPort;
import com.yun.membership.domain.Membership;
import com.yun.membership.domain.ModifyMembership;
import com.yun.membership.exception.MembershipModuleException;
import com.yun.membership.jwt.JwtToken;
import com.yun.membership.vault.VaultAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static com.yun.membership.exception.MembershipErrorCode.USER_NOTFOUND_ACCOUNT;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, ReadMembershipPort, ModifyMembershipPort {

    private final VaultAdapter vaultAdapter;
    private final MembershipJpaRepository membershipJpaRepository;
    private final MembershipMapper mapper;
    @Override
    public Membership createdMembership(Membership membership) {
        //email만 암호화?
        String encryptEmail = vaultAdapter.encrypt(membership.getMembershipEmail());
        MembershipEntity membershipEntity = membershipJpaRepository.save(mapper.mapToEntity(membership, encryptEmail));
        return mapper.mapToMembership(membershipEntity);
    }

    @Override
    public Membership findByMembershipId(String membershipId) {
        //email 정보 복호화 단계 추가
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
    public List<Membership> findAllMembershipAddress(String address) {
        return membershipJpaRepository.findAllByMembershipAddress(address).stream()
                .map(membershipEntity -> mapper.mapToMembership(membershipEntity))
                .collect(Collectors.toList());
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

    @Override
    public void insertRefreshToken(JwtToken jwtToken) {
        MembershipEntity getMembership = membershipJpaRepository.findById(jwtToken.getMembershipId())
                .orElseThrow(() -> {
                    throw new MembershipModuleException(USER_NOTFOUND_ACCOUNT, USER_NOTFOUND_ACCOUNT.getMessage());
                });
        getMembership.insertRefreshToken(jwtToken.getRefreshToken());
        membershipJpaRepository.save(getMembership);
    }
}

package com.yun.membership.adapter.out.persistence;

import com.yun.membership.domain.Membership;
import org.springframework.stereotype.Component;

import static com.yun.membership.domain.Membership.*;

@Component
public class MembershipMapper {
    public Membership mapToMembership(MembershipEntity membershipEntity) {
        return generateOutMember(
                new MembershipId(membershipEntity.getMembershipId()),
                new MembershipPw(membershipEntity.getMembershipPw()),
                new MembershipName(membershipEntity.getName()),
                new MembershipEmail(membershipEntity.getMembershipEmail()),
                new MembershipAddress(membershipEntity.getAddress()),
                new MembershipRefreshToken(membershipEntity.getRefreshToken()),
                new MembershipIsValid(membershipEntity.isValid()),
                new RegisterCreatedAt(membershipEntity.getCreatedAt()),
                new InfoModifiedAt(membershipEntity.getModifiedAt())
        );
    }

    public MembershipEntity mapToEntity(Membership membership, String encryptEmail) {
        return MembershipEntity.builder()
                .membershipId(membership.getMembershipId())
                .membershipPw(membership.getMembershipPw())
                .membershipEmail(encryptEmail)
                .name(membership.getName())
                .address(membership.getAddress())
                .refreshToken(membership.getRefreshToken())
                .isMoneyWalletAvailable(false)
                .role(membership.getRole())
                .createdAt(membership.getCreatedAt())
                .modifiedAt(membership.getModifiedAt())
                .deletedAt(null)
                .build();
    }

}

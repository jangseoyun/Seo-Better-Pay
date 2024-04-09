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
                new MembershipIsValid(membershipEntity.isValid()),
                new RegisterCreatedAt(membershipEntity.getCreatedAt()),
                new InfoModifiedAt(membershipEntity.getModifiedAt())
        );
    }

    public MembershipEntity mapToEntity(Membership membership) {
        return MembershipEntity.of(
                        membership.getMembershipId(),
                        membership.getMembershipPw(),
                        membership.getMembershipEmail(),
                        membership.getName(),
                        membership.getAddress(),
                        true,
                        true,
                        membership.getCreatedAt(),
                        membership.getModifiedAt(),
                        null
        );
    }

}

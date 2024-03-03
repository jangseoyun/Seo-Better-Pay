package com.yun.membership.adapter.out.persistence;

import com.yun.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {
    public Membership mapToDomainEntity(MembershipEntity membershipEntity) {
        return Membership.generateMember(
                new Membership.MembershipId(membershipEntity.getId()+""),
                new Membership.MembershipName(membershipEntity.getName()),
                new Membership.MembershipEmail(membershipEntity.getEmail()),
                new Membership.MembershipAddress(membershipEntity.getAddress()),
                new Membership.MembershipIsValid(membershipEntity.isValid()),
                new Membership.MembershipIsCorp(membershipEntity.isCorp())
        );
    }
}

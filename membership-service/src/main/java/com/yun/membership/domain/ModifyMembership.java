package com.yun.membership.domain;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModifyMembership {

    private String membershipId;
    private String address;

    public static ModifyMembership generateInModifyMember(MembershipId membershipId,
                                                          MembershipAddress membershipAddress) {
        return new ModifyMembership(
                membershipId.id,
                membershipAddress.address);
    }

    @Value
    public static class MembershipId {
        String id;

        public MembershipId(String value) {
            this.id = value;
        }
    }

    @Value
    public static class MembershipAddress {
        String address;

        public MembershipAddress(String value) {
            this.address = value;
        }
    }

}

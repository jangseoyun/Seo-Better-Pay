package com.yun.membership.domain;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModifyMembership {
    private String membershipId;
    private String membershipEmail;
    private String address;

    public static ModifyMembership generateInMember(MembershipId membershipId,
                                              MembershipAddress membershipAddress,
                                              MembershipEmail membershipEmail) {
        return new ModifyMembership(
                membershipId.id,
                membershipAddress.address,
                membershipEmail.email);
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

    @Value
    public static class MembershipEmail {
        String email;

        public MembershipEmail(String value) {
            this.email = value;
        }
    }

}

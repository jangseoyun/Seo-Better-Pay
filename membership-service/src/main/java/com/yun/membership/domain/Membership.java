package com.yun.membership.domain;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {
    private String membershipId;
    private String name;
    private String email;
    private String address;
    private boolean isValid;
    private boolean isCorp;

    public static Membership generateMember(MembershipId membershipId,
                                            MembershipName membershipName,
                                            MembershipEmail membershipEmail,
                                            MembershipAddress membershipAddress,
                                            MembershipIsValid membershipIsValid,
                                            MembershipIsCorp membershipIsCorp) {
        return new Membership(
                membershipId.id,
                membershipName.name,
                membershipEmail.email,
                membershipAddress.address,
                membershipIsValid.isValid,
                membershipIsCorp.isCorp);
    }

    @Value
    public static class MembershipId{
        String id;
        public MembershipId(String value) {
            this.id = value;
        }
    }

    @Value
    public static class MembershipName{
        String name;
        public MembershipName(String value) {
            this.name = value;
        }
    }

    @Value
    public static class MembershipEmail{
        String email;
        public MembershipEmail(String value) {
            this.email = value;
        }
    }

    @Value
    public static class MembershipAddress{
        String address;
        public MembershipAddress(String value) {
            this.address = value;
        }
    }

    @Value
    public static class MembershipIsValid{
        boolean isValid;
        public MembershipIsValid(boolean value) {
            this.isValid = value;
        }
    }

    @Value
    public static class MembershipIsCorp{
        boolean isCorp;
        public MembershipIsCorp(boolean value) {
            this.isCorp = value;
        }
    }
}

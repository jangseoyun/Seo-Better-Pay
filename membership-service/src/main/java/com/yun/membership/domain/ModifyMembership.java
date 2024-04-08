package com.yun.membership.domain;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModifyMembership {

    private String editMembershipId;
    private String membershipPw;
    private String address;

    public static ModifyMembership generateInModifyMember(EditMembershipId membershipId,
                                                          MembershipPw membershipPw,
                                                          MembershipAddress membershipAddress) {
        return new ModifyMembership(
                membershipId.editMembershipId,
                membershipPw.password,
                membershipAddress.address);
    }

    @Value
    public static class EditMembershipId {
        String editMembershipId;

        public EditMembershipId(String value) {
            this.editMembershipId = value;
        }
    }

    @Value
    public static class MembershipPw {
        String password;

        public MembershipPw(String value) {
            this.password = value;
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

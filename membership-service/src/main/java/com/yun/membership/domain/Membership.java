package com.yun.membership.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {

    private String membershipId;
    private String membershipPw;
    private String membershipEmail;
    private String name;
    private String address;
    private boolean isValid;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static Membership generateInMember(MembershipId membershipId,
                                              MembershipPw membershipPw,
                                              MembershipName membershipName,
                                              MembershipEmail membershipEmail,
                                              MembershipAddress membershipAddress,
                                              MembershipIsValid membershipIsValid) {
        return new Membership(
                membershipId.id,
                membershipPw.password,
                membershipName.name,
                membershipEmail.email,
                membershipAddress.address,
                membershipIsValid.isValid,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public static Membership generateOutMember(MembershipId membershipId,
                                               MembershipName membershipName,
                                               MembershipEmail membershipEmail,
                                               MembershipAddress membershipAddress,
                                               MembershipIsValid membershipIsValid,
                                               RegisterCreatedAt registerCreatedAt,
                                               InfoModifiedAt infoModifiedAt) {
        return new Membership(
                membershipId.id,
                null,
                membershipName.name,
                membershipEmail.email,
                membershipAddress.address,
                membershipIsValid.isValid,
                registerCreatedAt.createdAt,
                infoModifiedAt.modifiedAt);
    }

    @Value
    public static class MembershipId {
        String id;

        public MembershipId(String value) {
            this.id = value;
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
    public static class MembershipName {
        String name;

        public MembershipName(String value) {
            this.name = value;
        }
    }

    @Value
    public static class MembershipEmail {
        String email;

        public MembershipEmail(String value) {
            this.email = value;
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
    public static class MembershipIsValid {
        boolean isValid;

        public MembershipIsValid(boolean value) {
            this.isValid = value;
        }
    }

    @Value
    public static class RegisterCreatedAt {
        LocalDateTime createdAt;

        public RegisterCreatedAt(LocalDateTime value) {
            this.createdAt = value;
        }
    }

    @Value
    public static class InfoModifiedAt {
        LocalDateTime modifiedAt;

        public InfoModifiedAt(LocalDateTime value) {
            this.modifiedAt = value;
        }
    }
}

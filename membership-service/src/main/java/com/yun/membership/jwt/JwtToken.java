package com.yun.membership.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@ToString
@Getter
@AllArgsConstructor
public class JwtToken {

    private String membershipId;
    private String jwtToken;
    private String refreshToken;

    public static JwtToken generateJwtToken(
            MembershipId membershipId,
            MembershipJwtToken membershipJwtToken,
            MembershipRefreshToken membershipRefreshToken
    ) {
        return new JwtToken(membershipId.membershipId,
                membershipJwtToken.jwtToken,
                membershipRefreshToken.refreshToken);
    }

    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }
        String membershipId ;
    }

    @Value
    public static class MembershipJwtToken {
        public MembershipJwtToken(String value) {
            this.jwtToken = value;
        }
        String jwtToken ;
    }

    @Value
    public static class MembershipRefreshToken {
        public MembershipRefreshToken(String value) {
            this.refreshToken = value;
        }
        String refreshToken ;
    }
}

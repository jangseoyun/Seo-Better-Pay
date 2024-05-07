package com.yun.membership.jwt;

public class JwtCreateFactory {
    public static JwtToken.MembershipId newMembershipId(String membershipId) {
        return new JwtToken.MembershipId(membershipId);
    }

    public static JwtToken newJwtToken(JwtToken.MembershipId accessMembershipId, String jwtToken, String refreshToken) {
        return JwtToken.generateJwtToken(
                new JwtToken.MembershipId(accessMembershipId.getMembershipId()),
                new JwtToken.MembershipJwtToken(jwtToken),
                new JwtToken.MembershipRefreshToken(refreshToken)
        );
    }
}

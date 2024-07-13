package com.yun.membership.jwt;

public interface LoginAuthTokenPort {
    String createJwtToken(JwtToken.MembershipId membershipId);
    String createRefreshToken(JwtToken.MembershipId membershipId);
    boolean validateJwtToken(String jwtToken);
    //jwt token으로 어떤 멤버십 id를 가지고 있는지 확인
    JwtToken.MembershipId checkMembershipIdByToken(String jwtToken);
}

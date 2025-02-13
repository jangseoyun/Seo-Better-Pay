package com.yun.membership.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * jwt token 생성 로직 전부를 가지고 있는 클래스
 *
 * @jwtSecretKey : jwt 토큰 생성을 위한 내부 시크릿 키
 * @jwtExpirationInMs : jwt 토큰 만료시간 (유효시간)
 * @refreshTokenExpirationInMs : refresh 토큰의 만료시간 (유효시간)
 */
@Slf4j
@Component
public class JwtTokenProvider implements LoginAuthTokenPort {

    private final SecretKey jwtSecretKey;
    private Long jwtExpirationInMs;
    private Long refreshTokenExpirationInMs;

    public JwtTokenProvider(@Value("${service.jwt.secretkey}") String jwtSecretKey) {
        // 516 bit 알고리즘 지원을 위한 비밀키
        // 512 bit = 64byte
        //env 등을 통해서 외부 환경변수로부터 데이터를 받아올 수 있다
        log.info("jwtSecretKey: {}", jwtSecretKey);
        this.jwtSecretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecretKey));
        this.jwtExpirationInMs = 1000L * 20; //20초
        this.refreshTokenExpirationInMs = 1000L * 60; //60초
    }

    @Override
    public String createJwtToken(JwtToken.MembershipId membershipId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .subject(membershipId.getMembershipId())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(jwtSecretKey)
                .compact();
    }

    @Override
    public String createRefreshToken(JwtToken.MembershipId membershipId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .subject(membershipId.getMembershipId())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(jwtSecretKey, Jwts.SIG.HS512)
                .compact();
    }

    @Override
    public boolean validateJwtToken(String jwtToken) {
        try {
            Claims payload = Jwts.parser()
                    .verifyWith(jwtSecretKey)
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
            log.info("validateJwtToken success: {}", payload);
            return true;
        } catch (MalformedJwtException e) {
            //invalid jwt token: 유효하지 않은 jwt 토큰일 때 발생
        } catch (ExpiredJwtException e) {
            //토큰의 유효기긴아 민료된 경우 발생: 토큰 강제로 비활성화 시키는 로직
        } catch (UnsupportedJwtException e) {
            //지원하지 않는 jwt 토큰일 때 발생
        } catch (IllegalArgumentException e) {
            //jwt claims string is empty 토큰이 비어있을 때 발생
        }
        return false;
    }

    @Override
    public JwtToken.MembershipId checkMembershipIdByToken(String jwtToken) {
        Claims jwtPayload = Jwts.parser()
                .verifyWith(jwtSecretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return new JwtToken.MembershipId(jwtPayload.getSubject());
    }

}

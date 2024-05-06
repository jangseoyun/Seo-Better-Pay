package com.yun.openbanking.adapter.in.web.model;

/**
 * 중간에 패킷을 가로채 값을 변경할 수 있기 때문에 재검증이 필요하다
 */
public record MemberOAuth3LeggedRequest(
        String code, //사용자 인증 응답 authorization_code 일치여부 확인
        String redirectUrl //사용자 인증시 요청한 redirectUrl 확인
){
}

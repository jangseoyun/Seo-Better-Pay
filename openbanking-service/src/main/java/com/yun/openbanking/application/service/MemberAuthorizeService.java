package com.yun.openbanking.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.openbanking.adapter.out.factory.AuthorizeLeggedFactory;
import com.yun.openbanking.application.port.in.MemberAuthorizeUseCase;
import com.yun.openbanking.application.port.in.MemberOAuth3LeggedCommand;
import com.yun.openbanking.application.port.out.MemberAuthorizePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class MemberAuthorizeService implements MemberAuthorizeUseCase {

    private final MemberAuthorizePort memberAuthorizePort;

    /**
     * 사용자인증 API 토큰 발급 요청(3-legged)
     */
    @Override
    public ResponseEntity request3AuthorizeMember(MemberOAuth3LeggedCommand command) {
        return memberAuthorizePort.requestMemberAuthorizeToken(AuthorizeLeggedFactory.of(command));
    }
}

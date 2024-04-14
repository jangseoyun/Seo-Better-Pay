package com.yun.openbanking.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.openbanking.application.port.in.MemberAuthorizeCommand;
import com.yun.openbanking.application.port.in.MemberAuthorizeUseCase;
import com.yun.openbanking.application.port.out.MemberAuthorizePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class MemberAuthorizeService implements MemberAuthorizeUseCase {

    private final MemberAuthorizePort memberAuthorizePort;

    @Override
    public void requestAuthorizeMember(MemberAuthorizeCommand command) {
        memberAuthorizePort.requestMemberAuthorize();
    }
}

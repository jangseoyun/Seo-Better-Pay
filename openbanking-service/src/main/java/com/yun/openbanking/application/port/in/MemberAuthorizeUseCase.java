package com.yun.openbanking.application.port.in;

public interface MemberAuthorizeUseCase {
    void requestAuthorizeMember(MemberAuthorizeCommand command);
}

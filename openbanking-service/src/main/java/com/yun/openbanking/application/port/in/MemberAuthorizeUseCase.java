package com.yun.openbanking.application.port.in;

import org.springframework.http.ResponseEntity;

public interface MemberAuthorizeUseCase {
    ResponseEntity request3AuthorizeMember(MemberOAuth3LeggedCommand command);
}

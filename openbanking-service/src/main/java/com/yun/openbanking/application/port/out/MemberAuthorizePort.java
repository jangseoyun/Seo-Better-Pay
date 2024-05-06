package com.yun.openbanking.application.port.out;

import com.yun.openbanking.domain.Authorize3Legged;
import org.springframework.http.ResponseEntity;

public interface MemberAuthorizePort {
    ResponseEntity requestMemberAuthorizeToken(Authorize3Legged authorize3Legged);
}

package com.yun.membership.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MembershipRole {
    ROLE_USER("ROLE_USER", "알반회원"),
    ROLE_ADMIN("ROLE_ADMIN", "관리자");

    private String role;
    private String roleName;
}

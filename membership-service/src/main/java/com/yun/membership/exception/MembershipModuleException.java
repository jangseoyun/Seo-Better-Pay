package com.yun.membership.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MembershipModuleException extends RuntimeException{
    private MembershipErrorCode membershipErrorCode;
    private String message;
}

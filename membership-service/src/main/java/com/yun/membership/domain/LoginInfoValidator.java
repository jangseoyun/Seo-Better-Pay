package com.yun.membership.domain;

import org.springframework.stereotype.Component;

@Component
public class LoginInfoValidator {
    public boolean checkMembershipPw(String responseMembershipPw, String requestMembershipPw) {
        //예외처리?
        if (!requestMembershipPw.equals(responseMembershipPw)) {
            return false;
        }

        return true;
    }

}

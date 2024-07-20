package com.yun.preapisecure.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccessMembership {
    private String membershipId;
    private String membershipPw;

    public String getMembershipId() {
        return membershipId;
    }

    public String getMembershipPw() {
        return membershipPw;
    }
}

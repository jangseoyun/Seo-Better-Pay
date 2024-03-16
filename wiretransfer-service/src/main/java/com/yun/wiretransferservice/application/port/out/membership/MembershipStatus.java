package com.yun.wiretransferservice.application.port.out.membership;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MembershipStatus {
    private String membershipId;
    private boolean isValid;
}

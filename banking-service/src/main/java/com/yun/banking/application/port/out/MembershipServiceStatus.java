package com.yun.banking.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MembershipServiceStatus {
    private String membershipId;
    private boolean isValid;
}

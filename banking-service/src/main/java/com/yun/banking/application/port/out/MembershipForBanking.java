package com.yun.banking.application.port.out;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipForBanking {// banking service를 위한 membership으로 개별적으로 만들어줌
    private String membershipId;
    private String name;
    private String membershipEmail;
    private String address;
    private boolean isValid;
    private boolean isCorp;
}

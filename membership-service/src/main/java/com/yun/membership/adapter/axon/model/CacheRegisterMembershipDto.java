package com.yun.membership.adapter.axon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@AllArgsConstructor
public class CacheRegisterMembershipDto implements Serializable {
    private String membershipId;
    private String membershipPw;
    private String membershipEmail;
}

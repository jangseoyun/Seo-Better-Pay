package com.yun.openbanking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAuthorize {
    private String clientId;
    private String clientSecret;
}

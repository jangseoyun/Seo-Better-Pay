package com.yun.openbanking.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ValidOAuth3LeggedRequest {
    String code;
    String client_id;
    String client_secret;
    String redirect_uri;
    String grant_type;
}

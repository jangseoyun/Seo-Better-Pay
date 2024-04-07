package com.yun.openbanking.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.openbanking.adapter.in.web.model.MemberAuthorizeRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class MemberAuthorizeCommand extends SelfValidating<MemberAuthorizeRequest> {

    @NotNull @NotEmpty @NotBlank
    private String response_type;
    @NotNull @NotEmpty @NotBlank @Max(value = 40)
    private String client_id;
    @NotNull @NotEmpty @NotBlank
    private String redirect_uri;
    @NotNull @NotEmpty @NotBlank
    private String scope;
    @NotNull @NotEmpty @NotBlank @Max(value = 32)
    private String state;
    @NotNull @NotEmpty @NotBlank
    private String auth_type;

    private String client_info;
    private String lang;
    private String cellphone_cert_yn;
    private String authorized_cert_yn;
    private String account_hold_auth_yn;
    private String register_info;
    private String accountinfo_yn;
    private String accountinfo_api_tran_id;
    private String accountinfo_list_num;

    //고객 접속 단말기 정보
    private String client_device_type;
    private String client_device_ip;
    private String client_device_mac;
    private String client_device_id;
    private String client_device_num;
    private String client_device_version;

    @Builder
    public MemberAuthorizeCommand(String client_id,
                                  String redirect_uri,
                                  String scope,
                                  String state,
                                  String auth_type,
                                  String client_info,
                                  String cellphone_cert_yn,
                                  String authorized_cert_yn,
                                  String account_hold_auth_yn,
                                  String register_info,
                                  String accountinfo_yn,
                                  String accountinfo_api_tran_id,
                                  String accountinfo_list_num,
                                  String client_device_type,
                                  String client_device_ip,
                                  String client_device_mac,
                                  String client_device_id,
                                  String client_device_num,
                                  String client_device_version) {
        this.response_type = "code";
        this.client_id = client_id;
        this.redirect_uri = redirect_uri;
        this.scope = scope;
        this.state = state;
        this.auth_type = auth_type;
        this.client_info = client_info;
        this.lang = "kor";
        this.cellphone_cert_yn = cellphone_cert_yn;
        this.authorized_cert_yn = authorized_cert_yn;
        this.account_hold_auth_yn = account_hold_auth_yn;
        this.register_info = register_info;
        this.accountinfo_yn = accountinfo_yn;
        this.accountinfo_api_tran_id = accountinfo_api_tran_id;
        this.accountinfo_list_num = accountinfo_list_num;
        this.client_device_type = client_device_type;
        this.client_device_ip = client_device_ip;
        this.client_device_mac = client_device_mac;
        this.client_device_id = client_device_id;
        this.client_device_num = client_device_num;
        this.client_device_version = client_device_version;
    }
}

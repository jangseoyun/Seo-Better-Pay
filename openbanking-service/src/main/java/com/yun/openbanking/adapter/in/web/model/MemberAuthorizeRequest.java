package com.yun.openbanking.adapter.in.web.model;

import com.yun.openbanking.application.port.in.MemberAuthorizeCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

/**
 * @param response_type:           (필수)OAuth 2.0 인증 요청 시 반환되는 형태 (고정값 code)
 * @param client_id:               (필수)오픈뱅킹에서 발급한 이용기관 앱의 Client ID
 * @param redirect_uri:            (필수)사용자인증이 성공하면 이용기관으로 연결되는 URL
 * @param scope:                   (필수)Access Token 권한 범위
 * @param state:                   (필수)이용기관이 세팅하는 난수값
 * @param auth_type:               (필수)사용자인증타입 구분) 0:최초인증, 2:인증생략
 * @param client_info:             이용기관이 세팅하는 임의의 정보
 * @param lang:                    다국어 설정
 * @param cellphone_cert_yn:       휴대전화 인증 사용여부 (Y | N)
 * @param authorized_cert_yn:      공동・금융인증서 사용여부 (Y | N)
 * @param account_hold_auth_yn:    계좌소유인증 사용여부 (Y | N)
 * @param register_info:           등록정보 종류 “A” : 계좌 | “C” : (신용/체크)카드 | “F” : 선불 | “I” : 보험 | “L” : 캐피탈(대출·리스)
 * @param accountinfo_yn:          계좌통합조회 사용여부 (Y | N)
 * @param accountinfo_api_tran_id: 계좌통합조회 사용여부가 “Y”인 경우 필수. 계좌통합조회 결과 중 고객이 선택한 특정 계좌 정보 입력
 * @param accountinfo_list_num
 * @param client_device_type:      고객 접속 단말기 구분 [ PC | AD | IO ]
 * @param client_device_ip:        고객의 접속 단말기 IP 주소
 * @param client_device_mac:       고객의 접속 단말기 MAC 주소
 * @param client_device_id:        고객의 접속 단말기를 구분할 수 있는 고유식별정보
 * @param client_device_num:       고객의 접속 단말기의 휴대폰번호
 * @param client_device_version:   고객의 접속 단말기 OS 버전
 */
public record MemberAuthorizeRequest(
        //필수
        String response_type,
        String client_id,
        String redirect_uri,
        String scope,
        String state,
        String auth_type,

        String client_info,
        String lang,
        String cellphone_cert_yn,
        String authorized_cert_yn,
        String account_hold_auth_yn,
        String register_info,
        String accountinfo_yn,
        String accountinfo_api_tran_id,
        String accountinfo_list_num,

        //고객 접속 단말기 정보
        String client_device_type,
        String client_device_ip,
        String client_device_mac,
        String client_device_id,
        String client_device_num,
        String client_device_version
) {
    public MemberAuthorizeRequest (String response_type,
                                  @Value("${open-banking.test.client-id}")
                                  String client_id,
                                  String redirect_uri,
                                  @NotNull @NotEmpty @NotBlank
                                  String scope,
                                  @Length(max = 32)
                                  String state,
                                  @NotNull
                                  String auth_type,
                                  String client_info,
                                  String lang,
                                  String cellphone_cert_yn,
                                  String authorized_cert_yn,
                                  String account_hold_auth_yn,
                                  String register_info,
                                  String accountinfo_yn,
                                  String accountinfo_api_tran_id,
                                  String accountinfo_list_num,
                                  String client_device_type,
                                  @Length(max = 15)
                                  String client_device_ip,
                                  @Length(max = 17)
                                  String client_device_mac,
                                  @Length(max = 48)
                                  String client_device_id,
                                  @Length(max = 11)
                                  String client_device_num,
                                  @Length(max = 20)
                                  String client_device_version) {
        this.response_type = "code";
        this.client_id =
        this.redirect_uri = redirect_uri;
        this.scope = scope;
        this.state = state;
        this.auth_type = auth_type;
        this.client_info = client_info;
        this.lang = lang;
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

    public MemberAuthorizeCommand toCommand() {
        return MemberAuthorizeCommand.builder()
                .client_id(client_id)
                .redirect_uri(redirect_uri)
                .scope(scope)
                .state(state)
                .auth_type(auth_type)
                .client_info(client_info)
                .cellphone_cert_yn(cellphone_cert_yn)
                .authorized_cert_yn(authorized_cert_yn)
                .account_hold_auth_yn(account_hold_auth_yn)
                .register_info(register_info)
                .accountinfo_yn(accountinfo_yn)
                .accountinfo_api_tran_id(accountinfo_api_tran_id)
                .accountinfo_list_num(accountinfo_list_num)
                .client_device_type(client_device_type)
                .client_device_ip(client_device_ip)
                .client_device_mac(client_device_mac)
                .client_device_id(client_device_id)
                .client_device_num(client_device_num)
                .client_device_version(client_device_version)
                .build();
    }
}

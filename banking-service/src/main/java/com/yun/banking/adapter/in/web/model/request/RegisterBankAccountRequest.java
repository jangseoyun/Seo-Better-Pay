package com.yun.banking.adapter.in.web.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * bank_tran_id : 거래고유번호(참가기관)
 * bank_code_std : 등록계좌개설기관.표준코드
 * register_account_num : 등록계좌번호 (공백 제거)
 * user_info : 생년월일(8자리) 주민등록번호 등록 기준
 * user_name : 사용자명
 * user_ci : CI connect info
 * scope : 단일 scope만 가능 (조회, 출금)
 */
public record RegisterBankAccountRequest(
        @NotNull
        @NotBlank
        String membershipId,
        @NotNull
        @NotBlank
        String bankCodeStd,
        @NotNull
        @NotBlank
        String registerAccountNum,
        @NotNull
        @NotBlank
        String userSocialNum,
        @NotNull
        @NotBlank
        String userName,
        @NotNull
        @NotBlank
        String userCi,
        @NotNull
        @NotBlank
        String scopeState
) {

}

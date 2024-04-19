package com.yun.openbanking.adapter.in.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * 출금 이체 API: 계좌번호 사용 (자체인증 기관)
 * @param bankTranId:      (필수)거래고유번호(참가기관)
 * @param cntrAccountType: (필수)약정 계좌/계정구분 “N”:계좌, “C”:계정
 * @param cntrAccountNum:  (필수)약정 계좌/계정 번호
 * @param dpsPrintContent: (필수)입금계좌인자내역
 * @param wdBankCodeStd:   (필수)출금은행.표준코드
 * @param wdAccountNum:    (필수)출금계좌번호
 * @param tranAmt:         (필수)거래금액
 * @param userSeqNo:       (필수)사용자일련번호
 * @param tranDtime:       (필수)요청일시
 * @param reqClientName:   (필수)요청고객성명주
 * @param reqClientNum:    (필수)요청고객회원번호주
 * @param transferPurpose: (필수)이체용도
 */
public record RechargeTransferWithdrawRequest(
        @NotNull @NotBlank @NotEmpty
        @Length(max = 20)
        @Pattern(regexp = "^[A-Z0-9]*$")
        String bankTranId,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 1)
        @Pattern(regexp = "^[A-Z]*$")
        String cntrAccountType,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 16)
        @Pattern(regexp = "^[A-Z0-9]*$")
        String cntrAccountNum,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 20)
        @Pattern(regexp = "^[가-힣]*$", message = "한글만 가능합니다.")
        String dpsPrintContent,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 3)
        @Pattern(regexp = "^[A-Z0-9]*$")
        String wdBankCodeStd,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 16)
        @Pattern(regexp = "^[A-Z0-9]*$")
        String wdAccountNum,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 12)
        @Pattern(regexp = "^[0-9]*$")
        Integer tranAmt,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 10)
        @Pattern(regexp = "^[A-Z0-9]*$")
        String userSeqNo,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 14)
        @Pattern(regexp = "^[0-9]*$")
        Integer tranDtime,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 20)
        @Pattern(regexp = "^[가-힣]*$", message = "한글만 가능합니다.")
        String reqClientName,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 20)
        @Pattern(regexp = "^[A-Z0-9]*$")
        String reqClientNum,
        @NotNull @NotBlank @NotEmpty
        @Length(max = 2)
        @Pattern(regexp = "^[A-Z0-9]*$")
        String transferPurpose
) {
}

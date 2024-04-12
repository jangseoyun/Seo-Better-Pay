package com.yun.money.adapter.in.web.model;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

/**
 * member의 요청
 * membership(ID)의 연동된 계좌에서 seobetterpay money wallet 충전 요청
 *
 * @param membershipSeqNo         : 사용자일련번호
 * @param membershipId            : 회원 아이디
 * @param memberName              : 회원 이름
 * @param linkedBankCode          : 은행 코드
 * @param linkedBankAccountNumber : 은행 계좌 번호
 * @param rechargeAmount          : 충전 요청 금액
 * TODO: spring security 적용시 membership 관련 정보 제거, 등록된 계좌 정보 조회하여 요청
 */
public record RechargeMoneyAmountRequest(
        @NotNull @NotBlank @NotEmpty
        @Length(max = 10)
        @Pattern(regexp = "^[A-Z0-9]*$")
        String membershipSeqNo,
        @NotNull @NotBlank @NotEmpty
        @Length(min = 5, max = 12, message = "아이디는 5자 ~ 12자 사이를 입력합니다.")
        @Pattern(regexp = "[a-z\\d]{5,12}", message = "가입된 사용자가 아닙니다.")
        String membershipId,
        @NotNull @NotBlank @NotEmpty
        @Length(min = 3, max = 6, message = "이름은 성을 포함한 3자 ~ 6자 사이를 입력합니다.")
        @Pattern(regexp = "^[가-힣]*$", message = "이름은 한글만 가능합니다.")
        String memberName,
        @NotNull @NotBlank @NotEmpty
        @Length(min = 3, max = 3)
        @Positive
        String linkedBankCode,
        @NotNull @NotBlank @NotEmpty
        @Length(min = 10, max = 16)
        @Positive
        String linkedBankAccountNumber,
        @NotNull @NotBlank @NotEmpty
        @Length(min = 5, max = 12)
        @Positive
        Integer rechargeAmount
) {

}

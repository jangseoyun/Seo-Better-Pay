package com.yun.money.adapter.in.web.model;

import com.yun.money.application.port.in.IncreaseMoneyAmountCommand;

/**
 * membership(ID)의 연동된 계좌에서 seobetterpay money wallet 충전 요청
 * @param moneyIncreaseRequestId : 충전 요청 고유 번호
 * @param membershipId : 회원 아이디
 * @param memberName : 회원 이름
 * @param linkedBankCode : 은행 코드
 * @param linkedBankAccountNumber : 은행 계좌 번호
 * @param increaseAmount : 충전 요청 금액
 */
public record IncreaseMoneyAmountRequest(
        String moneyIncreaseRequestId,
        String membershipId,
        String memberName,
        String linkedBankCode,
        String linkedBankAccountNumber,
        Integer increaseAmount
) {
    public IncreaseMoneyAmountCommand toCommand() {
        return IncreaseMoneyAmountCommand.builder()
                .moneyIncreaseRequestId(moneyIncreaseRequestId)
                .membershipId(membershipId)
                .memberName(memberName)
                .linkedBankCode(linkedBankCode)
                .linkedBankAccountNumber(linkedBankAccountNumber)
                .increaseAmount(increaseAmount)
                .moneyAdjustingType(MoneyAdjustingType.INCREASING)
                .build();
    }
}

package com.yun.money.adapter.in.web.model;

import com.yun.money.application.port.in.DecreaseMoneyAmountCommand;

/**
 * membership(ID)가 소유하는 현재 seobetterpay money wallet에서 사용 요청
 * @param membershipId : 회원 아이디
 * @param memberName : 회원 이름
 * @param targetPaymentsInfo : money 사용처 정보
 * @param decreaseAmount : 사용 요청 금액
 */
public record DecreaseMoneyAmountRequest(
        String membershipId,
        String memberName,
        String targetPaymentsInfo,
        Integer decreaseAmount
) {
    public DecreaseMoneyAmountCommand toCommand() {
        return DecreaseMoneyAmountCommand.of(
                membershipId,
                memberName,
                targetPaymentsInfo,
                decreaseAmount,
                MoneyAdjustingType.DECREASING
        );
    }
}

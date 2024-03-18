package com.yun.money.application.service;

import com.yun.common.UseCase;
import com.yun.money.adapter.in.web.model.MoneyChangingResultStatus;
import com.yun.money.application.port.in.DecreaseMoneyAmountCommand;
import com.yun.money.application.port.in.DecreaseMoneyUseCase;
import com.yun.money.application.port.out.DecreaseMoneyAmountPort;
import com.yun.money.domain.MoneyAmountCalculator;
import com.yun.money.domain.PayWalletMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class DecreaseMoneyService implements DecreaseMoneyUseCase {

    private final DecreaseMoneyAmountPort decreaseMoneyAmountPort;
    private final MoneyAmountCalculator moneyAmountCalculator;

    @Override
    public PayWalletMoney payWithSeobetterpay(DecreaseMoneyAmountCommand command) {
        //감액(사용)
        //1. TODO: 멤버십 회원 검증
        //2. money 금액 확인 (기존 금액 > 요청 금액)
        //2-1. 기존 금액 합산 쿼리(합산액은 0이하가 될 수 없다)
        Integer moneyTotalAmount = decreaseMoneyAmountPort.moneyTotalAmount(command.getBankAccountNumber());
        //2-2. 계산
        MoneyChangingResultStatus moneyChangingApprovalStatus
                = moneyAmountCalculator.checkAmountApproval(moneyTotalAmount, command.getRequestAdjustAmount());
        log.info("moneyTotalAmount:{}", moneyTotalAmount);
        //3. money 요청 히스토리 저장
        return decreaseMoneyAmountPort.decreaseMoneyAmount(command.toPayWalletMoney(moneyChangingApprovalStatus));
    }
}

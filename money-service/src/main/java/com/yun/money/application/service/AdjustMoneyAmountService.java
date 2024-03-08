package com.yun.money.application.service;

import com.yun.common.UseCase;
import com.yun.money.adapter.in.web.model.MoneyChangingResultStatus;
import com.yun.money.application.port.in.AdjustMoneyUseCase;
import com.yun.money.application.port.in.DecreaseMoneyAmountCommand;
import com.yun.money.application.port.in.IncreaseMoneyAmountCommand;
import com.yun.money.application.port.out.DecreaseMoneyAmountPort;
import com.yun.money.application.port.out.IncreaseMoneyAmountPort;
import com.yun.money.domain.MoneyAmountCalculator;
import com.yun.money.domain.PayWalletMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class AdjustMoneyAmountService implements AdjustMoneyUseCase {

    private final IncreaseMoneyAmountPort increaseMoneyAmountPort;
    private final DecreaseMoneyAmountPort decreaseMoneyAmountPort;
    private final MoneyAmountCalculator moneyAmountCalculator;

    @Override
    public PayWalletMoney increaseMoneyAmountRequest(IncreaseMoneyAmountCommand command) {
        //증액(충전)
        //1. TODO: 멤버십 회원 검증
        //1-1 고객의 연동된 계좌가 있는지 + 정상여부 체크
        //1-2 페이 법인 계좌 상태 정상 여부 체크 및 입출금 가능 여부 체크
        //2. TODO: money 요청 히스토리 저장 (계산하는 검증 로직 필요) (요청 상태로 저장)
        return increaseMoneyAmountPort.increaseMoneyAmount(command.toPayWalletMoney(MoneyChangingResultStatus.SUCCEEDED));
        //3. TODO: 펌뱅킹 (고객의 연동된 계좌 -> 법인 계좌)
        //4. 정상/실패 예외 및 정상 처리
    }

    @Override
    public PayWalletMoney decreaseMoneyAmountRequest(DecreaseMoneyAmountCommand command) {
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

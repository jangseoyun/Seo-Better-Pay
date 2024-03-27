package com.yun.money.application.service;

import com.yun.common.UseCase;
import com.yun.money.application.port.in.MembershipIdCommand;
import com.yun.money.application.port.in.MoneyWalletCalculatorUseCase;
import com.yun.money.application.port.out.CalculateMoneyWalletPort;
import com.yun.money.domain.MemberMoneyWallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class CalculateMoneyWalletService implements MoneyWalletCalculatorUseCase {

    private final CalculateMoneyWalletPort calculateMoneyWalletPort;

    @Override
    public MemberMoneyWallet MemberMoneyBalanceCalculate(MembershipIdCommand command) {
        return calculateMoneyWalletPort.processGetMemberMoneyWalletBalance(command);
    }
}

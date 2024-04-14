package com.yun.money.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.money.application.port.in.MembershipIdCommand;
import com.yun.money.application.port.in.ReadMoneyAmountUseCase;
import com.yun.money.application.port.out.ReadMoneyAmountPort;
import com.yun.money.domain.MemberMoneyWallet;
import com.yun.money.domain.PayWalletMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadMoneyAmountService implements ReadMoneyAmountUseCase {

    private final ReadMoneyAmountPort readMoneyAmountPort;
    private final CommandGateway commandGateway;

    @Override
    public Integer getMoneyTotalAmount(MembershipIdCommand command) {
        return readMoneyAmountPort.moneyTotalAmount(command.getMembershipId());
    }

    @Override
    public List<PayWalletMoney> getAddMoneyHistory(MembershipIdCommand command) {
        return readMoneyAmountPort.getAddMoneyHistory(command.getMembershipId());
    }

    @Override
    public List<PayWalletMoney> getPayMoneyHistory(MembershipIdCommand command) {
        return readMoneyAmountPort.getPayMoneyHistory(command.getMembershipId());
    }

    @Override
    public MemberMoneyWallet getMemberMoneyWallet(MembershipIdCommand command) {
        return readMoneyAmountPort.getMemberMoneyWallet(command.getMembershipId());
    }
}

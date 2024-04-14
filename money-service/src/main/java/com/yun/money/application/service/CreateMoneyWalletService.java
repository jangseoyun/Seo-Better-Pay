package com.yun.money.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.money.adapter.axon.command.MemberMoneyCreateCommand;
import com.yun.money.application.port.in.CreateMemberMoneyCommand;
import com.yun.money.application.port.in.MoneyWalletCreateUseCase;
import com.yun.money.application.port.out.CreateMoneyWalletPort;
import com.yun.money.domain.MemberMoneyWallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class CreateMoneyWalletService implements MoneyWalletCreateUseCase {

    private final CreateMoneyWalletPort createMoneyWalletPort;
    private final CommandGateway commandGateway;

    @Override
    public void createMemberMoneyWallet(CreateMemberMoneyCommand command) {
        MemberMoneyCreateCommand axonCommand = new MemberMoneyCreateCommand(command.getMembershipId());
        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                log.info("throwable : {}", throwable);
                throw new RuntimeException(throwable);
            }

            log.info("result: {}", result);
            MemberMoneyWallet memberMoneyWallet = createMoneyWalletPort.processGetMemberMoneyWalletBalance(
                    new MemberMoneyWallet.MembershipId(command.getMembershipId()),
                    new MemberMoneyWallet.MoneyAggregateIdentifier(result.toString())
            );
            log.info("commandGateway.send : createMemberMoneyWallet {} ", memberMoneyWallet);
        });
    }
}

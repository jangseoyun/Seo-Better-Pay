package com.yun.banking.application.service;

import com.yun.banking.adapter.out.external.bank.model.CallApiBankAccountRequest;
import com.yun.banking.application.factory.RegisterBankServiceFactory;
import com.yun.banking.application.port.in.RegisterBankAccountCommand;
import com.yun.banking.application.port.in.RegisterBankAccountUseCase;
import com.yun.banking.application.port.out.GetMembershipForBankingPort;
import com.yun.banking.application.port.out.MembershipServiceStatus;
import com.yun.banking.application.port.out.RegisterBankAccountPort;
import com.yun.banking.application.port.out.RequestBankAccountInfoPort;
import com.yun.banking.domain.RegisteredBankAccount;
import com.yun.common.anotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final GetMembershipForBankingPort getMembershipForBankingPort;
    private final CommandGateway commandGateway;

    @Override
    public RegisteredBankAccount registerBankAccountByMembership(RegisterBankAccountCommand command) {
        //1. 회원 확인 (멤버십 서비스 확인?)//TODO 캐싱 대상
        MembershipServiceStatus membershipStatus = getMembershipForBankingPort.getMembership(command.getMembershipId());
        if (!membershipStatus.isValid()) {
            return null;
        }

        //2. 외부 실제 은행 계좌 (본인 계좌 정상 확인)
        //port -> adapter -> external
        //실제 외부의 은행계좌 정보 get
        //TODO: api 연동
        CallApiBankAccountRequest callApiBankAccountRequest = CallApiBankAccountRequest.of(command.getBankCodeStd(), "", command.getRegisterAccountNum(), true);
        requestBankAccountInfoPort.getBankAccountInfo(callApiBankAccountRequest)
                .filter(bankAccount -> bankAccount.isValid())
                .orElseThrow(() -> {
                        throw new RuntimeException("유효하지 않은 계좌입니다");}
                );

        //3. 등록이 가능한 계좌면 등록 / 아니면 예외
        //TODO: 예외처리
        return registerBankAccountPort.createdBankAccount(RegisterBankServiceFactory.newRegisterBankAccount(command));
    }

    @Override
    public void registerBankAccountByMembershipByEvent(RegisterBankAccountCommand command) {

    }

    /*@Override
    public void registerBankAccountByMembershipByEvent(RegisterBankAccountCommand command) {
        CreateRegisteredBankAccountCommand axonCommand = new CreateRegisteredBankAccountCommand(
                command.getMembershipId(),
                command.getBankName(),
                command.getBankAccountNumber());

        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("throwable: {}", throwable);
            }

            log.info("result: {}", result.toString());

            requestBankAccountInfoPort.getBankAccountInfo(CallApiBankAccountRequest.of(command.getBankName(), command.getBankAccountNumber())
                    ).filter(bankAccount -> bankAccount.isValid())
                    .orElseThrow(() -> {
                        throw new RuntimeException("유효하지 않은 계좌입니다");}
                    );

            //3. 등록이 가능한 계좌면 등록 / 아니면 예외
            //TODO: 예외처리
            registerBankAccountPort.createdBankAccount(command.toDomainBankAccount(result.toString()));
        });

    }*/
}

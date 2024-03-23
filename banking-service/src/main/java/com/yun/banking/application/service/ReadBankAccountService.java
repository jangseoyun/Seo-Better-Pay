package com.yun.banking.application.service;

import com.yun.banking.application.port.in.ReadBankAccountCommand;
import com.yun.banking.application.port.in.ReadBankAccountUseCase;
import com.yun.banking.application.port.out.ReadBankAccountPort;
import com.yun.banking.domain.RegisteredBankAccount;
import com.yun.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadBankAccountService implements ReadBankAccountUseCase {

    private final ReadBankAccountPort readBankAccountPort;

    @Override
    public List<RegisteredBankAccount> searchLinkedBankAccounts(ReadBankAccountCommand command) {
        return readBankAccountPort.getLinkedBankAccounts(command.getMembershipId());
    }
}

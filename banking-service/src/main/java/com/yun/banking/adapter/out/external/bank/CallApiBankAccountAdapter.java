package com.yun.banking.adapter.out.external.bank;

import com.yun.banking.adapter.out.external.bank.model.BankAccount;
import com.yun.banking.adapter.out.external.bank.model.CallApiBankAccountRequest;
import com.yun.banking.application.port.out.RequestBankAccountInfoPort;
import com.yun.common.ExternalSystemAdapter;
import com.yun.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class CallApiBankAccountAdapter implements RequestBankAccountInfoPort {
    //TODO: 계좌실명조회 api (실제 은행 계좌 api 호출)
    @Override
    public Optional<BankAccount> getBankAccountInfo(CallApiBankAccountRequest callApiBankAccountRequest) {
        BankAccount bankAccount = new BankAccount(
                callApiBankAccountRequest.bankName(),
                callApiBankAccountRequest.bankAccountNumber(),
                true);
        return Optional.of(bankAccount);
    }
}

package com.yun.banking.application.port.out;

import com.yun.banking.adapter.out.external.bank.model.BankAccount;
import com.yun.banking.adapter.out.external.bank.model.CallApiBankAccountRequest;
import com.yun.banking.domain.RegisteredBankAccount;

import java.util.Map;
import java.util.Optional;

public interface RequestBankAccountInfoPort {
    Optional<BankAccount> getBankAccountInfo(CallApiBankAccountRequest callApiBankAccountRequest);
    boolean checkBankAccountStatus(RegisteredBankAccount.RegisterAccountNum registerAccountNum);
}

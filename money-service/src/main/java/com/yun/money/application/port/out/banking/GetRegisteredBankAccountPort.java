package com.yun.money.application.port.out.banking;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountAggregateIdentifier getLinkedBankAccounts(String membershipId);
}

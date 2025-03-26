package com.yun.money.application.port.out.client.banking;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountAggregateIdentifier getLinkedBankAccounts(String membershipId);
}

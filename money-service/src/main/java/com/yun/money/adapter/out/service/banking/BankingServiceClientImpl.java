package com.yun.money.adapter.out.service.banking;

import com.yun.money.application.port.out.client.banking.BankingServiceClient;

import java.util.List;

public class BankingServiceClientImpl implements BankingServiceClient {
    @Override
    public List<RegisteredBankAccount> getLinkedBankAccounts(String membershipId) {
        return null;
    }
}

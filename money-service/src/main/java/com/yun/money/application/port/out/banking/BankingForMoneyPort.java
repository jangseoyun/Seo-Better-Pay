package com.yun.money.application.port.out.banking;

import com.yun.money.adapter.out.service.banking.BankAccountForMoney;

public interface BankingForMoneyPort {
    BankAccountForMoney getMembershipLinkedBankAccount(String membershipId);
}

package com.yun.wiretransferservice.application.port.out.banking;

public interface BankingForWireTransferPort {
    BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber);
    boolean requestFirmBanking(String bankName, String bankAccountNumber, int amount);
}

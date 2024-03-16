package com.yun.wiretransferservice.adapter.out.service.banking;

import com.yun.common.ExternalSystemAdapter;
import com.yun.common.httpclient.CommonHttpClient;
import com.yun.wiretransferservice.application.port.out.banking.BankingForWireTransferPort;
import com.yun.wiretransferservice.application.port.out.banking.BankingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceForWireTransferAdapter implements BankingForWireTransferPort {

    private final CommonHttpClient bankingHttpClient;
    @Value("${service.banking.url}")
    private String bankingServiceEndPoint;
    @Override
    public BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber) {
        return null;
    }

    @Override
    public boolean requestFirmBanking(String bankName, String bankAccountNumber, int amount) {
        return false;
    }
}

package com.yun.settlementservice.adapter.out.service.banking;

import com.yun.common.anotation.ExternalSystemAdapter;
import com.yun.common.httpclient.CommonRestClient;
import com.yun.settlementservice.application.port.out.SettlementBankingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class SettlementServiceForBanking implements SettlementBankingPort {

    private final CommonRestClient bankingHttpClient;
    @Value("${service.banking.url}")
    private String bankingServiceUrl;

    @Override
    public RegisteredBankAccount getRegisterBankAccount(String membershipId) {
        String url = String.join("/", bankingServiceUrl, "/bank/account", membershipId);

        try {
            //bankingHttpClient.sendGetRequest(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void sendSettlementRequest() {

    }
}

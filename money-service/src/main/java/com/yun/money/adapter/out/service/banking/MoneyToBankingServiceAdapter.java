package com.yun.money.adapter.out.service.banking;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.httpclient.CommonHttpClient;
import com.yun.money.application.port.out.banking.BankingForMoneyPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MoneyToBankingServiceAdapter implements BankingForMoneyPort {

    private final CommonHttpClient bankingHttpClient;
    private final ObjectMapper objectMapper;

    @Value("${service.banking.url}")
    private String bankingServiceUrl;

    @Override
    public BankAccountForMoney getMembershipLinkedBankAccount(String membershipId) {
        String url = String.join("/", bankingServiceUrl, "/bank/account", membershipId);
        log.info("url: {}", url);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        try {
            String jsonResponse = bankingHttpClient.sendGetRequest(url).body();
            return objectMapper.readValue(jsonResponse, BankAccountForMoney.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

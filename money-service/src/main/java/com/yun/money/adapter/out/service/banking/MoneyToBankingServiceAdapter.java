package com.yun.money.adapter.out.service.banking;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.httpclient.CommonRestClient;
import com.yun.money.application.port.out.banking.BankingForMoneyPort;
import com.yun.money.application.port.out.banking.GetRegisteredBankAccountPort;
import com.yun.money.application.port.out.banking.RegisteredBankAccountAggregateIdentifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MoneyToBankingServiceAdapter implements BankingForMoneyPort, GetRegisteredBankAccountPort {

    private final CommonRestClient bankingHttpClient;
    private final ObjectMapper objectMapper;

    @Value("${service.banking.url}")
    private String bankingServiceUrl;

    @Override
    public BankAccountForMoney getMembershipLinkedBankAccount(String membershipId) {
        String url = String.join("/", bankingServiceUrl, "bank/account", membershipId);
        log.info("url: {}", url);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        try {
            String jsonResponse = bankingHttpClient.sendGetRequest(url).toString();
            return objectMapper.readValue(jsonResponse, BankAccountForMoney.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RegisteredBankAccountAggregateIdentifier getLinkedBankAccounts(String membershipId) {
        String url = String.join("/", bankingServiceUrl, "bank/account", membershipId);
        log.info("url: {}", url);

        try {
            String jsonString = bankingHttpClient.sendGetRequest(url);
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            log.info("jsonNode: {}", jsonNode);
            JsonNode jsonResult = jsonNode.get(0);
            log.info("jsonResult: {}", jsonResult);

            return new RegisteredBankAccountAggregateIdentifier(
                    jsonResult.get("registeredBankAccountId").textValue(),
                    jsonResult.get("membershipId").textValue(),
                    jsonResult.get("aggregateIdentifier").textValue(),
                    jsonResult.get("bankCodeStd").textValue(),
                    jsonResult.get("registerAccountNum").textValue());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

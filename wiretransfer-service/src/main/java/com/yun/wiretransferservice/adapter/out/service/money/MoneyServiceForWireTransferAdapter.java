package com.yun.wiretransferservice.adapter.out.service.money;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.anotation.ExternalSystemAdapter;
import com.yun.common.httpclient.CommonRestClient;
import com.yun.wiretransferservice.application.port.out.money.MoneyForWireTransferPort;
import com.yun.wiretransferservice.application.port.out.money.MoneyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MoneyServiceForWireTransferAdapter implements MoneyForWireTransferPort {

    private final CommonRestClient moneyHttpClient;
    private final ObjectMapper objectMapper;
    @Value("${service.money.url}")
    private String moneyServiceEndPoint;

    @Override
    public MoneyInfo getMoneyInfo(String membershipId) {
        String buildUrl = String.join("/", this.moneyServiceEndPoint, "money", membershipId);
        try {
            moneyHttpClient.sendGetRequest(buildUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean requestRechargingMoney(String membershipId, int amount) {
        return false;
    }

    @Override
    public boolean requestIncreaseMoney(String membershipId, int amount) {
        return false;
    }

    @Override
    public boolean requestDecreaseMoney(String membershipId, int amount) {
        return false;
    }
}

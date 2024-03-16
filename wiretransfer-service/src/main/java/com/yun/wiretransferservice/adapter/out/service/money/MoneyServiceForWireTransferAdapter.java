package com.yun.wiretransferservice.adapter.out.service.money;

import com.yun.common.ExternalSystemAdapter;
import com.yun.common.httpclient.CommonHttpClient;
import com.yun.wiretransferservice.application.port.out.money.MoneyForWireTransferPort;
import com.yun.wiretransferservice.application.port.out.money.MoneyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MoneyServiceForWireTransferAdapter implements MoneyForWireTransferPort {

    private final CommonHttpClient moneyHttpClient;
    @Value("${service.money.url}")
    private String moneyServiceEndPoint;

    @Override
    public MoneyInfo getMoneyInfo(String membershipId) {
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

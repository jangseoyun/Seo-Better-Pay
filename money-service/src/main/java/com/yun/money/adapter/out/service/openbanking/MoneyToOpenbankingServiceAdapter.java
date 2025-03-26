package com.yun.money.adapter.out.service.openbanking;

import com.yun.common.anotation.ExternalSystemAdapter;
import com.yun.common.httpclient.CommonRestClient;
import com.yun.money.application.port.out.client.openbanking.OpenbankingForMoneyPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ExternalSystemAdapter
@RequiredArgsConstructor
public class MoneyToOpenbankingServiceAdapter implements OpenbankingForMoneyPort {

    private final CommonRestClient openbankingRestClient;

    @Value("${service.openbanking.url}")
    private String openbankingServiceUrl;

    @Override
    public void requestOpenbankingService(PrepareMoneyTransferRequest request) {
        log.info("MoneyToOpenbankingServiceAdapter PrepareMoneyTransferRequest: {}", request);

        String url = String.join("/", openbankingServiceUrl, "openbanking/transfer-withdraw");
        ResponseEntity response = openbankingRestClient.sendPostRequest(url, request);

        log.info("requestTransferWithdrawAccount response: {}", response.getBody());
    }
}

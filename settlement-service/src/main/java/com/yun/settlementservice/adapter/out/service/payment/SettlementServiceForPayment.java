package com.yun.settlementservice.adapter.out.service.payment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.anotation.ExternalSystemAdapter;
import com.yun.common.httpclient.CommonRestClient;
import com.yun.settlementservice.application.port.out.SettlementPaymentPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Slf4j
@ExternalSystemAdapter
@RequiredArgsConstructor
public class SettlementServiceForPayment implements SettlementPaymentPort {

    private final CommonRestClient paymentHttpClient;
    @Value("${service.payment.url}")
    private String paymentServiceUrl;
    private final ObjectMapper objectMapper;

    @Override
    public List<PaymentRequestInfo> sendPaymentSuccessByMembershipId(PaymentRequestInfo.MembershipId membershipId) {
        String url = String.join("/", paymentServiceUrl, "bank/account", membershipId.getMembershipId());
        log.info("url: {}", url);

        try {
            String jsonString = paymentHttpClient.sendGetRequest(url);
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            log.info("jsonNode: {}", jsonNode);
            JsonNode jsonResult = jsonNode.get(0);
            log.info("jsonResult: {}", jsonResult);

            return List.of();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

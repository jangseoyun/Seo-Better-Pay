package com.yun.banking.adapter.out.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.banking.application.port.out.GetMembershipForBankingPort;
import com.yun.banking.application.port.out.MembershipServiceStatus;
import com.yun.common.httpclient.CommonRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MembershipServiceAdapter implements GetMembershipForBankingPort {

    private final CommonRestClient commonRestClient;
    private final ObjectMapper objectMapper;

    @Value("${service.membership.url}")
    private String membershipServiceUrl;

    @Override
    public MembershipServiceStatus getMembership(String membershipId) {
        //실제로 http call을 통해서 멤버십 서비스 호출
        //http client 필요
        String url = String.join("/", membershipServiceUrl, "membership", membershipId);

        try {
            String jsonString = commonRestClient.sendGetRequest(url);
            log.info("getMembership response: {}", jsonString);
            //json 형태의 membership 정보
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            log.info("membershipForBanking objectMapper jsonNode: {}", jsonNode);
            log.info("membershipForBanking jsonNode membershipId: {}", jsonNode.get("result").get("membershipId").textValue());
            if (jsonNode.get("result").get("valid").asBoolean()) {
                return new MembershipServiceStatus(jsonNode.get("result").get("membershipId").textValue(), true);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new MembershipServiceStatus(membershipId, false);
    }
}

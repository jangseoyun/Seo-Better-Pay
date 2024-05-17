package com.yun.money.adapter.out.service.membership;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.httpclient.CommonRestClient;
import com.yun.money.application.port.out.GetMembershipForMoneyPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MoneyToMembershipServiceAdapter implements GetMembershipForMoneyPort {

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

    @Override
    public List<String> getMembershipByAddress(String addressKeyword) {
        String url = String.join("/", membershipServiceUrl, "membership");
        String requestUrl = UriComponentsBuilder.fromHttpUrl(url)
                .path("/address")
                .queryParam("addressKeyword", addressKeyword)
                .build()
                .toUriString();
        log.info("request url: {}", requestUrl);
        try {
            String memberCallResponse = commonRestClient.sendGetRequest(requestUrl);
            log.info("sendGetRequest response: {}", memberCallResponse);
            //json 형태의 membership 정보

            //TODO: result에 있는 typeReference 사용해볼것

            JsonNode rootNode = objectMapper.readTree(memberCallResponse);
            JsonNode resultNode = rootNode.get("result");
            log.info("memberCallResponse jsonNode result: {}", resultNode);

            List<String> membershipIds = new ArrayList<>();
            resultNode.forEach(memberNode -> {
                String membershipId = memberNode.get("membershipId").asText();
                membershipIds.add(membershipId);
            });

            return membershipIds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

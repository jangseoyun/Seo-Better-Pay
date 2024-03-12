package com.yun.banking.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.banking.application.port.out.GetMembershipForBankingPort;
import com.yun.banking.application.port.out.MembershipForBanking;
import com.yun.banking.application.port.out.MembershipServiceStatus;
import com.yun.common.httpclient.CommonHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MembershipServiceAdapter implements GetMembershipForBankingPort {

    private final CommonHttpClient commonHttpClient;
    private final ObjectMapper objectMapper;

    @Value("${service.membership.url}")
    private String membershipServiceUrl;

    @Override
    public MembershipServiceStatus getMembership(String membershipId) {
        //실제로 http call을 통해서 멤버십 서비스 호출
        //http client 필요
        String url = String.join("/", membershipServiceUrl, "membership", membershipId);
        MembershipForBanking membershipForBanking;
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            //json 형태의 membership 정보
            membershipForBanking = objectMapper.readValue(jsonResponse, MembershipForBanking.class);

            if (membershipForBanking.isValid()) {
                return new MembershipServiceStatus(membershipForBanking.getMembershipId(), true);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new MembershipServiceStatus(membershipForBanking.getMembershipId(), false);
    }
}

package com.yun.wiretransferservice.adapter.out.service.membership;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.anotation.ExternalSystemAdapter;
import com.yun.common.httpclient.CommonRestClient;
import com.yun.wiretransferservice.application.port.out.membership.MembershipForWireTransferPort;
import com.yun.wiretransferservice.application.port.out.membership.MembershipStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MembershipServiceForWireTransferAdapter implements MembershipForWireTransferPort {

    private final CommonRestClient membershipHttpClient;
    private final ObjectMapper objectMapper;
    @Value("${service.membership.url}")
    private String membershipServiceEndPoint;

    @Override
    public MembershipStatus getMembershipStatus(String membershipId) {
        String buildUrl = String.join("/", this.membershipServiceEndPoint, "membership", membershipId);
        try {
            String jsonString = membershipHttpClient.sendGetRequest(buildUrl);
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            if (jsonNode.get("valid").textValue().equals("true")) {
                return new MembershipStatus(membershipId, true);
            } else {
                return new MembershipStatus(membershipId, false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

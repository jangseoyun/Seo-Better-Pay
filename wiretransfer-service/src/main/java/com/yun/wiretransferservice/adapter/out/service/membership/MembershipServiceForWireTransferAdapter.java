package com.yun.wiretransferservice.adapter.out.service.membership;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.ExternalSystemAdapter;
import com.yun.common.httpclient.CommonHttpClient;
import com.yun.wiretransferservice.application.port.out.membership.MembershipForWireTransfer;
import com.yun.wiretransferservice.application.port.out.membership.MembershipForWireTransferPort;
import com.yun.wiretransferservice.application.port.out.membership.MembershipStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MembershipServiceForWireTransferAdapter implements MembershipForWireTransferPort {

    private final CommonHttpClient membershipHttpClient;
    private final ObjectMapper objectMapper;
    @Value("${service.membership.url}")
    private String membershipServiceEndPoint;

    @Override
    public MembershipStatus getMembershipStatus(String membershipId) {
        String buildUrl = String.join("/", this.membershipServiceEndPoint, "membership", membershipId);
        try {
            String jsonResponse = membershipHttpClient.sendGetRequest(buildUrl).body();
            MembershipForWireTransfer membership = objectMapper.readValue(jsonResponse, MembershipForWireTransfer.class);
            if (membership.isValid()) {
                return new MembershipStatus(membership.getMembershipId(), true);
            } else {
                return new MembershipStatus(membership.getMembershipId(), false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

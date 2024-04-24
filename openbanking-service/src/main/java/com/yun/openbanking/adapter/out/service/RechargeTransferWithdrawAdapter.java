package com.yun.openbanking.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.OpenbankingTestUrl;
import com.yun.common.anotation.ExternalSystemAdapter;
import com.yun.common.httpclient.CommonRestClient;
import com.yun.openbanking.application.port.out.TransferWithdrawPort;
import com.yun.openbanking.domain.TransferWithdraw;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@ExternalSystemAdapter
@RequiredArgsConstructor
public class RechargeTransferWithdrawAdapter implements TransferWithdrawPort {

    private final CommonRestClient rechargeRestClient;

    @Override
    public void callTransferWithdrawOpenbanking(TransferWithdraw transferWithdraw) {
        //TODO: response 만들기
        ResponseEntity response = rechargeRestClient.sendPostRequest(OpenbankingTestUrl.TRANSFER_WITHDRAW.getUrl(), transferWithdraw);
        log.info("callTransferWithdrawOpenbanking response: {}", response.getBody().toString());
    }

}

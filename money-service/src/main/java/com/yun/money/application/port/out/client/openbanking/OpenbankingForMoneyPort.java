package com.yun.money.application.port.out.client.openbanking;

import com.yun.money.adapter.out.service.openbanking.PrepareMoneyTransferRequest;

public interface OpenbankingForMoneyPort {
    void requestOpenbankingService(PrepareMoneyTransferRequest request);
}

package com.yun.settlementservice.application.port.out;

import com.yun.settlementservice.adapter.out.service.banking.RegisteredBankAccount;

public interface SettlementBankingPort {
    // 1. 등록된 계좌 정보 가져오기
    RegisteredBankAccount getRegisterBankAccount(String membershipId);

    // 2. 정산 금액만큼 계좌에 입금 요청 (펌 뱅킹)
    void sendSettlementRequest();
}

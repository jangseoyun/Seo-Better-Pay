package com.yun.banking.adapter.out.external.bank.model;

public record CallExternalTransferRequest(
        //외부 은행 통신과 관련된 요청
        String fromBankName,
        String fromBankAccountNumber,
        String toBankName,
        String toBankAccountNumber,
        String aggregateIdentifier
) {
}

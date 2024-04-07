package com.yun.money.adapter.out.service.banking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//TODO: AggregateIdentifier를 PK 고유값으로 설정할 것 이렇게 될 경우 axon framework를 사용하지 않을 경우 고려해야한다.
@JsonIgnoreProperties(ignoreUnknown = true)
public record RegisteredBankAccount(
        String increaseRequestId, //db pk
        String membershipId,
        String registeredBankAccountAggregateIdentifier, //axon
        String bankName,
        String bankAccountNumber,
        boolean linkedStatusIsValid
) {
}

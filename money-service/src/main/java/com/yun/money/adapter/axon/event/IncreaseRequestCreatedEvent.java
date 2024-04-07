package com.yun.money.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 충전 동작 요청이 생성되었다는 이벤트
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IncreaseRequestCreatedEvent {
    private String increaseRequestId;
    private String membershipId;
    private int amount;
    private String registeredBankAccountAggregateIdentifier;
    private String bankName;
    private String bankAccountNumber;
}

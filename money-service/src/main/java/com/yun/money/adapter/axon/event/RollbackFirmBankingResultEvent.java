package com.yun.money.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RollbackFirmBankingResultEvent {
    private String rollbackFirmBankingId;
    private String membershipId;
    private String rollbackFirmBankingAggregateIdentifier;
}

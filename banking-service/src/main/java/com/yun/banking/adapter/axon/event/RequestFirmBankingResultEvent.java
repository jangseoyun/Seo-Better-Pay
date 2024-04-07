package com.yun.banking.adapter.axon.event;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestFirmBankingResultEvent {
    private String requestFirmBankingId;
    private String increaseRequestId;
    private String membershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;
    private String resultCode;
    private String aggregateIdentifier;
}

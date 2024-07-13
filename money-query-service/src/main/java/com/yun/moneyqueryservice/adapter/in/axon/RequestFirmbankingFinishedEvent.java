package com.yun.moneyqueryservice.adapter.in.axon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestFirmbankingFinishedEvent {
    private String requestFirmbankingId;
    private String rechargingRequestId;
    private String membershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount; // only won
    private int status;
    private String requestFirmbankingAggregateIdentifier;
}

package com.yun.banking.adapter.axon.event;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CheckedRegisterBankAccountEvent {
    private String increaseRequestId;
    private String checkRegisteredBankAccountId;
    private String membershipId;
    private boolean isChecked;
    private int amount;
    private String firmBankingRequestAggregateIdentifier;
    private String fromBankName; //고객 계좌 -> 법인 계좌
    private String fromBankAccountNumber;
}

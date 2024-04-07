package com.yun.banking.adapter.axon.event;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateRegisteredBankAccountEvent {
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
}

package com.yun.money.application.port.out.client.banking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredBankAccountAggregateIdentifier {
    private String increaseRequestId;
    private String membershipId;
    private String registeredBankAccountAggregateIdentifier;
    private String bankName;
    private String bankAccountNumber;
}

package com.yun.banking.adapter.axon.command;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateRegisteredBankAccountCommand {
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
}

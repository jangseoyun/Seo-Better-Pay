package com.yun.banking.adapter.axon.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * firm-banking 요청 command
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestFirmBankingCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String requestFirmBankingId;
    private String increaseRequestId;
    private String membershipId;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;
}

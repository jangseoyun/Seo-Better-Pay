package com.yun.money.adapter.axon.event;

import com.yun.common.SelfValidating;
import com.yun.money.adapter.axon.command.MemberMoneyCreateCommand;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyCreateEvent extends SelfValidating<MemberMoneyCreateCommand> {
    private String aggregateIdentifier;
    private String membershipId;
}

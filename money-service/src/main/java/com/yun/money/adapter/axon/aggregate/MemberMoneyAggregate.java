package com.yun.money.adapter.axon.aggregate;

import com.yun.money.adapter.axon.command.MemberMoneyAddCommand;
import com.yun.money.adapter.axon.event.MemberMoneyAddEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Getter
@Aggregate
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;
    private String membershipId;
    private int balance;

    @CommandHandler
    public MemberMoneyAggregate(MemberMoneyAddCommand command) {
        log.info("MemberMoneyAddCommand Handler");
        apply(new MemberMoneyAddEvent(command.getMembershipId()));
    }

    @EventSourcingHandler
    public void on(MemberMoneyAddEvent event) {
        log.info("MemberMoneyAddEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        balance = 0;
    }

    public MemberMoneyAggregate() {
    }
}

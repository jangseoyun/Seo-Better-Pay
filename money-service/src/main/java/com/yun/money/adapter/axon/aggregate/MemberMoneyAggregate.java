package com.yun.money.adapter.axon.aggregate;

import com.yun.money.adapter.axon.command.IncreaseMemberMoneyCommand;
import com.yun.money.adapter.axon.command.MemberMoneyCreateCommand;
import com.yun.money.adapter.axon.event.IncreaseMemberMoneyEvent;
import com.yun.money.adapter.axon.event.MemberMoneyCreateEvent;
import jakarta.validation.constraints.NotNull;
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
    public MemberMoneyAggregate(MemberMoneyCreateCommand command) {
        log.info("MemberMoneyAddCommand Handler");
        apply(new MemberMoneyCreateEvent(id, command.getMembershipId()));
    }

    @CommandHandler
    public String handle(@NotNull IncreaseMemberMoneyCommand command) {
        log.info("IncreaseMemberMoneyCommand handler");
        id = command.getAggregateIdentifier();

        //store event
        apply(new IncreaseMemberMoneyEvent(id, command.getMembershipId(), command.getIncreaseAmount()));
        return id;
    }

    @EventSourcingHandler
    public void on(MemberMoneyCreateEvent event) {
        log.info("MemberMoneyAddEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        balance = 0;
    }

    @EventSourcingHandler
    public void on(IncreaseMemberMoneyEvent event) {
        log.info("IncreaseMemberMoneyEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        balance = event.getIncreaseAmount();
    }

    protected MemberMoneyAggregate() {
    }
}

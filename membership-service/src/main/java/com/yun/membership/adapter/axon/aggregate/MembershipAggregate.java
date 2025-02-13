package com.yun.membership.adapter.axon.aggregate;

import com.yun.membership.application.port.in.RegisterMembershipCommand;
import com.yun.membership.application.port.in.event.RegisterMembershipEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Getter
@Aggregate
public class MembershipAggregate {

    @AggregateIdentifier
    private String membershipId;
    private String membershipPw;
    private String membershipEmail;

    protected MembershipAggregate() {}

    @CommandHandler
    public MembershipAggregate(RegisterMembershipCommand command) {
        log.info("RegisterMembershipCommand handler");
        apply(new RegisterMembershipEvent(command.getMembershipId(), command.getMembershipPw(), command.getMembershipEmail()));
    }

    @EventSourcingHandler
    public void on(RegisterMembershipEvent event) {
        log.info("RegisterMembershipEvent handler: {}", event);
        membershipId = event.getMembershipId();
        membershipPw = event.getMembershipPw();
        membershipEmail = event.getMembershipEmail();
    }
}

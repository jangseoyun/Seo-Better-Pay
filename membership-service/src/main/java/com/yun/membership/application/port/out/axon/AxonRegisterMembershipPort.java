package com.yun.membership.application.port.out.axon;

import com.yun.membership.application.port.in.RegisterMembershipCommand;

public interface AxonRegisterMembershipPort {
    void send(RegisterMembershipCommand command);
}

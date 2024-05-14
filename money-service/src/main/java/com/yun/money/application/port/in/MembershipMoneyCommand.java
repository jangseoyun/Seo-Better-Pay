package com.yun.money.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.money.adapter.in.web.model.MembershipListRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class MembershipMoneyCommand extends SelfValidating<MembershipListRequest> {
    private List<String> membershipIds;

    public MembershipMoneyCommand(List<String> membershipIds) {
        this.membershipIds = membershipIds;
        this.validateSelf();
    }
}

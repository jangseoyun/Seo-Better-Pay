package com.yun.money.adapter.in.web.factory;

import com.yun.money.adapter.in.web.model.MembershipListRequest;
import com.yun.money.application.port.in.MembershipMoneyCommand;

public class MembershipMoneyFactory {
    public static MembershipMoneyCommand newMembershipIdListCommand(MembershipListRequest request) {
        return new MembershipMoneyCommand(request.membershipIds());
    }
}

package com.yun.banking.adapter.in.factory;

import com.yun.banking.adapter.in.web.model.request.RegisterBankAccountRequest;
import com.yun.banking.application.port.in.RegisterBankAccountCommand;

public class RegisterBankAccountFactory {
    public static RegisterBankAccountCommand newInstance(RegisterBankAccountRequest request) {
        return RegisterBankAccountCommand.builder()
                .membershipId(request.membershipId())
                .bankCodeStd(request.bankCodeStd())
                .userSocialNum(request.userSocialNum())
                .registerAccountNum(request.registerAccountNum())
                .userName(request.userName())
                .userCi(request.userCi())
                .scopeState(request.scopeState())
                .linkedStatusIsValid(false)
                .build();
    }
}

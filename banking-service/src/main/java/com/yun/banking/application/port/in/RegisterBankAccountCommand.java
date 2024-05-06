package com.yun.banking.application.port.in;

import com.yun.banking.adapter.in.web.model.request.RegisterBankAccountRequest;
import com.yun.common.SelfValidating;
import jakarta.validation.constraints.AssertFalse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountRequest> {

    private String membershipId;
    private String bankCodeStd;
    private String userSocialNum;
    private String registerAccountNum;
    private String userName;
    private String userCi;
    private String scopeState;
    @AssertFalse
    private boolean linkedStatusIsValid; //내부 시스템에서 확인 후 변경

    @Builder
    public RegisterBankAccountCommand(String membershipId,
                                      String bankCodeStd,
                                      String userSocialNum,
                                      String registerAccountNum,
                                      String userName,
                                      String userCi,
                                      String scopeState,
                                      boolean linkedStatusIsValid) {
        this.membershipId = membershipId;
        this.bankCodeStd = bankCodeStd;
        this.userSocialNum = userSocialNum;
        this.registerAccountNum = registerAccountNum;
        this.userName = userName;
        this.userCi = userCi;
        this.scopeState = scopeState;
        this.linkedStatusIsValid = linkedStatusIsValid;
    }

}

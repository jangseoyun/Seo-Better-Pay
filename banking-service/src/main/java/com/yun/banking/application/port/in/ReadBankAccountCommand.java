package com.yun.banking.application.port.in;

import com.yun.banking.adapter.in.web.model.request.SearchBankAccountRequest;
import com.yun.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ReadBankAccountCommand extends SelfValidating<SearchBankAccountRequest> {
    @NotNull
    @NotBlank
    private String membershipId;

    private ReadBankAccountCommand(String membershipId) {
        this.membershipId = membershipId;
    }

    public static ReadBankAccountCommand of(String membershipId) {
        return new ReadBankAccountCommand(membershipId);
    }
}

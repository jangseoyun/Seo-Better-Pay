package com.yun.banking.application.port.in;

import com.yun.banking.adapter.in.web.model.request.RegisterBankAccountRequest;
import com.yun.banking.domain.RegisteredBankAccount;
import com.yun.common.SelfValidating;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.yun.banking.domain.RegisteredBankAccount.*;

@Getter
@EqualsAndHashCode
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountRequest> {
    @NotNull
    @NotBlank
    private String membershipId;
    @NotNull
    @NotBlank
    private String bankName;
    @NotNull
    @NotBlank
    private String bankAccountNumber;
    @AssertFalse
    private boolean linkedStatusIsValid; //내부 시스템에서 확인 후 변경

    private RegisterBankAccountCommand(String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
    }

    public static RegisterBankAccountCommand of(String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        return new RegisterBankAccountCommand(membershipId, bankName, bankAccountNumber, linkedStatusIsValid);
    }

    public RegisteredBankAccount toDomainBankAccount() {
        return generateBankAccount(
                new BankAccountId(null),
                new MembershipId(membershipId),
                new BankName(bankName),
                new BankAccountNumber(bankAccountNumber),
                new LinkedStatusIsValid(linkedStatusIsValid)
        );
    }

}

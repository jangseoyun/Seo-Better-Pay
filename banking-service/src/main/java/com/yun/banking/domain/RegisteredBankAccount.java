package com.yun.banking.domain;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {

    private final String registeredBankAccountId;
    private final String membershipId;
    private final String bankName;
    private final String bankAccountNumber;
    private final boolean linkedStatusIsValid;
    private final String aggregateIdentifier;

    public static RegisteredBankAccount generateBankAccount(
            BankAccountId registeredBankAccountId,
            MembershipId membershipId,
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            LinkedStatusIsValid linkedStatusIsValid,
            RegisteredAggregateIdentifier registeredAggregateIdentifier
    ) {
        return new RegisteredBankAccount(
                registeredBankAccountId.bankAccountId,
                membershipId.membershipId,
                bankName.bankName,
                bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid.linkedStatusIsValid,
                registeredAggregateIdentifier.aggregateIdentifier
        );
    }

    @Value
    public static class BankAccountId {
        String bankAccountId;

        public BankAccountId(String value) {
            this.bankAccountId = value;
        }
    }

    @Value
    public static class MembershipId {
        String membershipId;

        public MembershipId(String value) {
            this.membershipId = value;
        }
    }

    @Value
    public static class BankName {
        String bankName;

        public BankName(String value) {
            this.bankName = value;
        }
    }

    @Value
    public static class BankAccountNumber {
        String bankAccountNumber;

        public BankAccountNumber(String value) {
            this.bankAccountNumber = value;
        }
    }

    @Value
    public static class LinkedStatusIsValid {
        boolean linkedStatusIsValid;

        public LinkedStatusIsValid(boolean value) {
            this.linkedStatusIsValid = value;
        }
    }

    @Value
    public static class RegisteredAggregateIdentifier {
        String aggregateIdentifier;

        public RegisteredAggregateIdentifier(String value) {
            this.aggregateIdentifier = value;
        }
    }

}

package com.yun.settlementservice.adapter.out.service.banking;

import lombok.*;

/**
 * bank_tran_id : 거래고유번호(참가기관)
 * bank_code_std : 등록계좌개설기관.표준코드
 * register_account_num : 등록계좌번호 (공백 제거)
 * user_info : 생년월일(8자리) 주민등록번호 등록 기준
 * user_name : 사용자명
 * user_ci : CI connect info
 * scope : 단일 scope만 가능 (조회, 출금)
 */
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {

    private final String registeredBankAccountId;
    private final String membershipId;
    private final String bankCodeStd;
    private final String registerAccountNum;
    private final String userSocialNum;
    private final String userName;
    private final String userCi;
    private final String scope;
    private final boolean linkedStatusIsValid;
    private final String aggregateIdentifier;

    public static RegisteredBankAccount generateBankAccount(
            BankAccountId registeredBankAccountId,
            MembershipId membershipId,
            BankCodeStd bankCodeStd,
            RegisterAccountNum registerAccountNum,
            UserSocialNum userSocialNum,
            UserName userName,
            UserCi userCi,
            Scope scope,
            LinkedStatusIsValid linkedStatusIsValid,
            RegisteredAggregateIdentifier registeredAggregateIdentifier
    ) {
        return new RegisteredBankAccount(
                registeredBankAccountId.bankAccountId,
                membershipId.membershipId,
                bankCodeStd.bankCodeStd,
                registerAccountNum.registerAccountNum,
                userSocialNum.userSocialNum,
                userName.userName,
                userCi.userCi,
                scope.scope,
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
    public static class BankCodeStd {
        String bankCodeStd;

        public BankCodeStd(String value) {
            this.bankCodeStd = value;
        }
    }

    @Value
    public static class RegisterAccountNum {
        String registerAccountNum;

        public RegisterAccountNum(String value) {
            this.registerAccountNum = value;
        }
    }

    @Value
    public static class UserSocialNum {
        String userSocialNum;

        public UserSocialNum(String value) {
            this.userSocialNum = value;
        }
    }

    @Value
    public static class UserName {
        String userName;

        public UserName(String value) {
            this.userName = value;
        }
    }

    @Value
    public static class UserCi {
        String userCi;

        public UserCi(String value) {
            this.userCi = value;
        }
    }

    @Value
    public static class Scope {
        String scope;

        public Scope(String value) {
            this.scope = value;
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

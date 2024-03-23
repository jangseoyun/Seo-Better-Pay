package com.yun.money.adapter.out.service.banking;

public record BankAccountForMoney (
        Long id,
        String membershipId,
        String bankName,
        String bankAccountNumber,
        boolean linkedStatusIsValid
){
}

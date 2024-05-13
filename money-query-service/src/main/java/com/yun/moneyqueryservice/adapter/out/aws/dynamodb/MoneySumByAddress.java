package com.yun.moneyqueryservice.adapter.out.aws.dynamodb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoneySumByAddress {
    private String pk;
    private String sk;
    private int balance;
}

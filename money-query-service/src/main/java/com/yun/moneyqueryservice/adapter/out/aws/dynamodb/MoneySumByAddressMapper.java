package com.yun.moneyqueryservice.adapter.out.aws.dynamodb;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class MoneySumByAddressMapper {
    public MoneySumByAddress mapToMoneySumByAddress(Map<String, AttributeValue> item) {
        return new MoneySumByAddress(
                item.get("PK").s(),
                item.get("SK").n(),
                Integer.parseInt(item.get("balance").n()));
    }
}

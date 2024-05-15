package com.yun.money.application.port.in;

public interface MoneyAggregationUseCase {
    int getMoneySumByAddress(String addressKeyword);
}

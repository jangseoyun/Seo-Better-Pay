package com.yun.moneyqueryservice.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.moneyqueryservice.application.port.in.QueryMoneySumByRegionQuery;
import com.yun.moneyqueryservice.application.port.in.QueryMoneySumRegionUseCase;
import com.yun.moneyqueryservice.domain.MoneySumByRegion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class MoneyQueryService implements QueryMoneySumRegionUseCase {
    @Override
    public MoneySumByRegion queryMoneySumByRegion(QueryMoneySumByRegionQuery query) {
        return null;
    }
}

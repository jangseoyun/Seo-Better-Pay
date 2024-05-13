package com.yun.moneyqueryservice.application.port.in;

import com.yun.moneyqueryservice.domain.MoneySumByRegion;

public interface QueryMoneySumRegionUseCase {
    MoneySumByRegion queryMoneySumByRegion(QueryMoneySumByRegionQuery query);
}

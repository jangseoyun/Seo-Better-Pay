package com.yun.moneyqueryservice.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.moneyqueryservice.application.port.in.QueryMoneySumByRegionQuery;
import com.yun.moneyqueryservice.application.port.in.QueryMoneySumRegionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@WebAdapter
@RequestMapping("/money/query")
public class MoneyQueryController {

    private final QueryMoneySumRegionUseCase queryMoneySumRegionUseCase;

    @GetMapping("/get-money-sum-by-address")
    public long getMoneySumByAddress(@RequestParam("addressKeyword") String addressKeyword) {
        QueryMoneySumByRegionQuery query = QueryMoneySumByRegionQuery.builder()
                .address(addressKeyword)
                .build();
        return queryMoneySumRegionUseCase.queryMoneySumByRegion(query).getMoneySum();
    }
}

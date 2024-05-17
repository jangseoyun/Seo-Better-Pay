package com.yun.money.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.money.application.port.in.MoneyAggregationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/money/aggregation")
public class MoneyAggregationController {

    private final MoneyAggregationUseCase moneyAggregationUseCase;

    @GetMapping("/money-sum-by-address")
    public ResponseEntity getMoneySumByAddress(@RequestParam("address") String addressKeyword) {
        log.info("request param:{}", addressKeyword);
        int membershipRegionTotalMoney = moneyAggregationUseCase.getMoneySumByAddress(addressKeyword);
        log.info("response total money : {}", membershipRegionTotalMoney);
        return ResponseEntity.ok().body(membershipRegionTotalMoney);
    }
}

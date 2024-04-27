package com.yun.money.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.money.adapter.in.web.model.RechargeMoneyAmountRequest;
import com.yun.money.application.port.factory.RechargeCommandFactory;
import com.yun.money.application.port.in.RechargeMoneyUseCase;
import com.yun.money.domain.PayWalletMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/money")
public class RechargeMoneyController {

    private final RechargeMoneyUseCase rechargeMoneyUseCase;

    @PostMapping("/recharge")
    public ResponseEntity increaseMoneyAmount(@RequestBody RechargeMoneyAmountRequest rechargeMoneyAmountRequest) {
        PayWalletMoney payWalletMoney = rechargeMoneyUseCase
                .rechargingMoneyToSeobetterpay(RechargeCommandFactory.newInstance(rechargeMoneyAmountRequest));
        return ResponseEntity.ok().body(payWalletMoney);
    }

    @PostMapping("/recharge-async")
    public ResponseEntity increaseMoneyAmountAsync(@RequestBody RechargeMoneyAmountRequest rechargeMoneyAmountRequest) {
        log.info("increaseMoneyAmountAsync request : {}", rechargeMoneyAmountRequest);
        PayWalletMoney payWalletMoney = rechargeMoneyUseCase
                .rechargingMoneyToSeobetterpayAsync(RechargeCommandFactory.newInstance(rechargeMoneyAmountRequest));
        return ResponseEntity.ok().body(payWalletMoney);
    }
}

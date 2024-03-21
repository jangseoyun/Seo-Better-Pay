package com.yun.money.adapter.in.web;

import com.yun.common.WebAdapter;
import com.yun.money.adapter.in.web.model.IncreaseMoneyAmountRequest;
import com.yun.money.application.port.in.IncreaseMoneyUseCase;
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
public class IncreaseMoneyController {

    private final IncreaseMoneyUseCase increaseMoneyUseCase;

    @PostMapping("/add")
    public ResponseEntity increaseMoneyAmount(@RequestBody IncreaseMoneyAmountRequest increaseMoneyAmountRequest) {
        PayWalletMoney payWalletMoney = increaseMoneyUseCase.addMoneyToSeobetterpay(increaseMoneyAmountRequest.toCommand());
        return ResponseEntity.ok().body(payWalletMoney);
    }

    @PostMapping("/add-async")
    public ResponseEntity increaseMoneyAmountAsync(@RequestBody IncreaseMoneyAmountRequest increaseMoneyAmountRequest) {
        log.info("increaseMoneyAmountAsync request : {}", increaseMoneyAmountRequest);
        PayWalletMoney payWalletMoney = increaseMoneyUseCase.addMoneyToSeobetterpayAsync(increaseMoneyAmountRequest.toCommand());
        return ResponseEntity.ok().body(payWalletMoney);
    }
}

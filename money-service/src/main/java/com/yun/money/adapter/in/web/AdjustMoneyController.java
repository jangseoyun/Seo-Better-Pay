package com.yun.money.adapter.in.web;

import com.yun.common.WebAdapter;
import com.yun.money.adapter.in.web.model.DecreaseMoneyAmountRequest;
import com.yun.money.adapter.in.web.model.IncreaseMoneyAmountRequest;
import com.yun.money.application.port.in.AdjustMoneyUseCase;
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
public class AdjustMoneyController {

    private final AdjustMoneyUseCase adjustMoneyUseCase;

    @PostMapping("/increase")
    public ResponseEntity increaseMoneyAmount(@RequestBody IncreaseMoneyAmountRequest increaseMoneyAmountRequest) {
        PayWalletMoney payWalletMoney = adjustMoneyUseCase.increaseMoneyAmountRequest(increaseMoneyAmountRequest.toCommand());
        return ResponseEntity.ok().body(payWalletMoney);
    }

    @PostMapping("/increase-async")
    public ResponseEntity increaseMoneyAmountAsync(@RequestBody IncreaseMoneyAmountRequest increaseMoneyAmountRequest) {
        log.info("increaseMoneyAmountAsync request : {}", increaseMoneyAmountRequest);
        PayWalletMoney payWalletMoney = adjustMoneyUseCase.increaseMoneyAmountRequestAsync(increaseMoneyAmountRequest.toCommand());
        return ResponseEntity.ok().body(payWalletMoney);
    }

    @PostMapping("/decrease")
    public ResponseEntity decreaseMoneyAmount(@RequestBody DecreaseMoneyAmountRequest decreaseMoneyAmountRequest) {
        PayWalletMoney payWalletMoney = adjustMoneyUseCase.decreaseMoneyAmountRequest(decreaseMoneyAmountRequest.toCommand());
        return ResponseEntity.ok().body(payWalletMoney);
    }
}

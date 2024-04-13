package com.yun.money.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.money.adapter.in.web.model.DecreaseMoneyAmountRequest;
import com.yun.money.application.port.in.DecreaseMoneyUseCase;
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
public class DecreaseMoneyController {

    private final DecreaseMoneyUseCase decreaseMoneyUseCase;

    @PostMapping("/pay")
    public ResponseEntity decreaseMoneyAmount(@RequestBody DecreaseMoneyAmountRequest decreaseMoneyAmountRequest) {
        PayWalletMoney payWalletMoney = decreaseMoneyUseCase.payWithSeobetterpay(decreaseMoneyAmountRequest.toCommand());
        return ResponseEntity.ok().body(payWalletMoney);
    }
}

package com.yun.money.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.money.adapter.in.web.model.CreateMemberMoneyRequest;
import com.yun.money.application.port.in.MoneyWalletCreateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/money")
public class CreateMoneyWalletController {

    private final MoneyWalletCreateUseCase moneyWalletCreateUseCase;

    @PostMapping("/{membershipId}/balance")
    public void processMemberMoneyWalletCalculator(@PathVariable("membershipId") CreateMemberMoneyRequest request) {
        moneyWalletCreateUseCase.createMemberMoneyWallet(request.toCommand());
    }
}

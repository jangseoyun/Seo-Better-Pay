package com.yun.money.adapter.in.web;

import com.yun.common.WebAdapter;
import com.yun.money.adapter.in.web.model.MembershipIdRequest;
import com.yun.money.application.port.in.MoneyWalletCalculatorUseCase;
import com.yun.money.domain.MemberMoneyWallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/money")
public class CalculateMoneyWalletController {

    private final MoneyWalletCalculatorUseCase moneyWalletCalculatorUseCase;

    @GetMapping("/{membershipId}/balance")
    public ResponseEntity processMemberMoneyWalletCalculator(@PathVariable("membershipId") MembershipIdRequest request) {
        MemberMoneyWallet memberMoneyWallet = moneyWalletCalculatorUseCase.MemberMoneyBalanceCalculate(request.toCommand());
        return ResponseEntity.ok().body(memberMoneyWallet);
    }
}

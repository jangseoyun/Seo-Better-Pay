package com.yun.banking.adapter.in.web;

import com.yun.banking.adapter.in.factory.RegisterBankAccountFactory;
import com.yun.banking.adapter.in.web.model.request.RegisterBankAccountRequest;
import com.yun.banking.application.port.in.RegisterBankAccountUseCase;
import com.yun.banking.domain.RegisteredBankAccount;
import com.yun.common.anotation.WebAdapter;
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
@RequestMapping("/bank/account")
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping("/register")
    public ResponseEntity registerBankAccount(@RequestBody RegisterBankAccountRequest request) {
        //TODO: 계좌실명조회 api 기능 고도화
        RegisteredBankAccount registeredBankAccount = registerBankAccountUseCase.registerBankAccountByMembership(RegisterBankAccountFactory.newInstance(request));
        return ResponseEntity.ok().body(registeredBankAccount);
    }

    @PostMapping("/register-eda")
    public void registerBankAccountByEvent(@RequestBody RegisterBankAccountRequest request) {
        //TODO: 계좌실명조회 api 기능 고도화
        registerBankAccountUseCase.registerBankAccountByMembershipByEvent(RegisterBankAccountFactory.newInstance(request));
    }
}

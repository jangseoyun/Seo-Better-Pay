package com.yun.banking.adapter.in.web;

import com.yun.banking.adapter.in.web.model.request.SearchBankAccountRequest;
import com.yun.banking.application.port.in.ReadBankAccountUseCase;
import com.yun.banking.domain.RegisteredBankAccount;
import com.yun.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/account")
public class ReadBankAccountController {

    private final ReadBankAccountUseCase readBankAccountUseCase;

    @GetMapping("/{membershipId}")
    public ResponseEntity getLinkedBankAccounts(@PathVariable("membershipId") SearchBankAccountRequest searchBankAccountRequest) {
        List<RegisteredBankAccount> registeredBankAccounts = readBankAccountUseCase.searchLinkedBankAccounts(searchBankAccountRequest.toCommand());
        return ResponseEntity.ok().body(registeredBankAccounts);
    }
}

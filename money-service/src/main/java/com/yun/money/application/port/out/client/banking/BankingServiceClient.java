package com.yun.money.application.port.out.client.banking;

import com.yun.money.adapter.out.service.banking.RegisteredBankAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "banking-service")
public interface BankingServiceClient {

    @GetMapping("/bank/account/{membershipId}")
    List<RegisteredBankAccount> getLinkedBankAccounts(@PathVariable("membershipId") String membershipId);
}

package com.yun.banking.adapter.in.web;

import com.yun.banking.adapter.in.web.model.request.TransferFirmBankingRequest;
import com.yun.banking.application.port.in.TransferFirmBankingUseCase;
import com.yun.banking.domain.TransferFirmBanking;
import com.yun.common.WebAdapter;
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
public class TransferFirmBankingController {

    private final TransferFirmBankingUseCase transferFirmBankingUseCase;

    @PostMapping("/transfer")
    public ResponseEntity sendTransferRequest(@RequestBody TransferFirmBankingRequest request) {
        TransferFirmBanking transferFirmBanking = transferFirmBankingUseCase.sendTransferRequest(request.toCommand());
        return ResponseEntity.ok().body(transferFirmBanking);
    }
}

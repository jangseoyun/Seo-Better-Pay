package com.yun.banking.adapter.in.web;

import com.yun.banking.adapter.in.web.model.request.ProcessingTransferFirmBankingRequest;
import com.yun.banking.adapter.in.web.model.request.TransferFirmBankingRequest;
import com.yun.banking.application.port.in.TransferFirmBankingUseCase;
import com.yun.banking.domain.TransferFirmBanking;
import com.yun.common.anotation.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/request/transfer-eda")
    public void sendTransferRequestByEvent(@RequestBody TransferFirmBankingRequest request) {
        transferFirmBankingUseCase.sendTransferRequestByEvent(request.toCommand());
    }

    @PutMapping("/transfer-eda")
    public void processingTransferFirmBankingByEvent(@RequestBody ProcessingTransferFirmBankingRequest request) {
        transferFirmBankingUseCase.processingTransferFirmBankingByEvent(request.toCommand());
    }
}

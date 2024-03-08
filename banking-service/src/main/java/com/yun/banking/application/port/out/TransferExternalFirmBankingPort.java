package com.yun.banking.application.port.out;

import com.yun.banking.adapter.out.external.bank.model.CallExternalTransferRequest;
import com.yun.banking.adapter.out.external.bank.model.FirmBankingResult;

public interface TransferExternalFirmBankingPort {
    FirmBankingResult transferFunds(CallExternalTransferRequest callExternalTransferRequest);
}

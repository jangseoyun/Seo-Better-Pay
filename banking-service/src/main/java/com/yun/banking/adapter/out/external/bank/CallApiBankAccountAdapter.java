package com.yun.banking.adapter.out.external.bank;

import com.yun.banking.adapter.out.external.bank.model.BankAccount;
import com.yun.banking.adapter.out.external.bank.model.CallApiBankAccountRequest;
import com.yun.banking.adapter.out.external.bank.model.CallExternalTransferRequest;
import com.yun.banking.adapter.out.external.bank.model.FirmBankingResult;
import com.yun.banking.adapter.out.factory.BankAccountInfoFactory;
import com.yun.banking.application.port.out.RequestBankAccountInfoPort;
import com.yun.banking.application.port.out.TransferExternalFirmBankingPort;
import com.yun.banking.domain.TransferRequestStatusEnum;
import com.yun.common.anotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class CallApiBankAccountAdapter implements RequestBankAccountInfoPort, TransferExternalFirmBankingPort {
    //TODO: 계좌실명조회 api (실제 은행 계좌 api 호출)
    @Override
    public Optional<BankAccount> getBankAccountInfo(CallApiBankAccountRequest callApiBankAccountRequest) {
        return Optional.of(BankAccountInfoFactory.newBankAccount(callApiBankAccountRequest));
    }

    @Override
    public FirmBankingResult transferFunds(CallExternalTransferRequest callExternalTransferRequest) {
        //TODO: 실제로 외부 은행에 http 콜을 통해 펌뱅킹 요청을 하고 그 결과에서 필요한 데이터만 파싱해 온다
        return new FirmBankingResult(TransferRequestStatusEnum.SUCCESS);
    }
}

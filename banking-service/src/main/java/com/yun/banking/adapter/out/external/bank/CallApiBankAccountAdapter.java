package com.yun.banking.adapter.out.external.bank;

import com.yun.banking.adapter.out.external.bank.model.BankAccount;
import com.yun.banking.adapter.out.external.bank.model.CallApiBankAccountRequest;
import com.yun.banking.adapter.out.external.bank.model.CallExternalTransferRequest;
import com.yun.banking.adapter.out.external.bank.model.FirmBankingResult;
import com.yun.banking.adapter.out.factory.BankAccountInfoFactory;
import com.yun.banking.adapter.out.persistence.RegisteredBankAccountEntity;
import com.yun.banking.adapter.out.persistence.RegisteredBankAccountJpaRepository;
import com.yun.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.yun.banking.application.port.out.RequestBankAccountInfoPort;
import com.yun.banking.application.port.out.TransferExternalFirmBankingPort;
import com.yun.banking.domain.RegisteredBankAccount;
import com.yun.banking.domain.TransferRequestStatusEnum;
import com.yun.banking.exception.BankingModuleException;
import com.yun.common.anotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.yun.banking.exception.BankingServiceErrorCode.*;

@Slf4j
@ExternalSystemAdapter
@RequiredArgsConstructor
public class CallApiBankAccountAdapter implements RequestBankAccountInfoPort, TransferExternalFirmBankingPort {
    //TODO: 계좌실명조회 api (실제 은행 계좌 api 호출)
    private final RegisteredBankAccountJpaRepository registeredBankAccountJpaRepository;
    private final RegisteredBankAccountMapper mapper;

    @Override
    public Optional<BankAccount> getBankAccountInfo(CallApiBankAccountRequest callApiBankAccountRequest) {
        return Optional.of(BankAccountInfoFactory.newBankAccount(callApiBankAccountRequest));
    }

    @Override
    public boolean checkBankAccountStatus(RegisteredBankAccount.RegisterAccountNum registerAccountNum) {
        //DB 호출 (실제로는 외부 API 호출)
        return registeredBankAccountJpaRepository.checkedBankAccountStatus(registerAccountNum.getRegisterAccountNum()).isPresent();
    }

    @Override
    public FirmBankingResult transferFunds(CallExternalTransferRequest callExternalTransferRequest) {
        //TODO: 실제로 외부 은행에 http 콜을 통해 펌뱅킹 요청을 하고 그 결과에서 필요한 데이터만 파싱해 온다
        return new FirmBankingResult(TransferRequestStatusEnum.SUCCESS);
    }
}

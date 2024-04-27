package com.yun.money.adapter.out.service.openbanking.factory;

import com.yun.money.adapter.out.service.openbanking.PrepareMoneyTransferRequest;
import com.yun.money.application.port.factory.AgreementAccountType;
import com.yun.money.application.port.factory.CompanyName;
import com.yun.money.application.port.factory.TransferPurposeType;
import com.yun.money.application.port.in.RechargeMoneyAmountCommand;

import java.time.LocalDateTime;

public class OpenbankingModelFactory {
    public static PrepareMoneyTransferRequest newInstance(RechargeMoneyAmountCommand command) {
        return PrepareMoneyTransferRequest.builder()
                .bankTranId("F123456789U4BC34239Z")
                .cntrAccountType(AgreementAccountType.BANK_ACCOUNT.getType())
                .cntrAccountNum(command.getLinkedBankAccountNumber())
                .dpsPrintContent(CompanyName.SEO_BETTER_PAY.name())
                .wdBankCodeStd(command.getLinkedBankCode())
                .wdAccountNum(command.getLinkedBankAccountNumber())
                .tranAmt(command.getRechargeAmount())
                .userSeqNo("")
                .tranDtime(LocalDateTime.now().toString())
                .reqClientName(command.getMemberName())
                .reqClientNum("")
                .transferPurpose(TransferPurposeType.RECHARGE.getCode())
                .build();
    }

}

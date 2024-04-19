package com.yun.money.application.port.factory;

import com.yun.money.adapter.in.web.model.MoneyAdjustingResultStatus;
import com.yun.money.adapter.in.web.model.MoneyAdjustingType;
import com.yun.money.application.port.in.RechargeMoneyAmountCommand;
import com.yun.money.domain.PayWalletMoney;
import com.yun.money.domain.PreprocessingRechargeMoney;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.yun.money.domain.PayWalletMoney.generatedPayWalletIncreaseMoney;

public class RechargeDomainFactory {
    private static final MoneyAdjustingType RECHARGE_INCREASING = MoneyAdjustingType.INCREASING;

    public static PayWalletMoney newPayWalletMoney(RechargeMoneyAmountCommand command) {
        return generatedPayWalletIncreaseMoney(
                new PayWalletMoney.MoneyAdjustRequestId(createRandomNumber()),
                new PayWalletMoney.MembershipId(command.getMembershipId()),
                new PayWalletMoney.MemberName(command.getMemberName()),
                new PayWalletMoney.LinkedBankCode(command.getLinkedBankCode()),
                RECHARGE_INCREASING,
                new PayWalletMoney.LinkedBankAccountNumber(command.getLinkedBankAccountNumber()),
                new PayWalletMoney.LinkedStatusIsValid(true),
                new PayWalletMoney.AdjustAmount(command.getRechargeAmount()),
                MoneyAdjustingResultStatus.READY
        );
    }

    public static PreprocessingRechargeMoney newPreprocessingRechargeMoney(RechargeMoneyAmountCommand command) {
        return PreprocessingRechargeMoney.generatedRechargeMoney(
                new PreprocessingRechargeMoney.BankTranId("거래고유번호 생성"),
                new PreprocessingRechargeMoney.CntrAccountType(AgreementAccountType.BANK_ACCOUNT),
                new PreprocessingRechargeMoney.CntrAccountNum(command.getLinkedBankAccountNumber()),
                new PreprocessingRechargeMoney.DpsPrintContent(CompanyName.SEO_BETTER_PAY),
                new PreprocessingRechargeMoney.WdBankCodeStd(command.getLinkedBankCode()),
                new PreprocessingRechargeMoney.WdAccountNum(command.getLinkedBankAccountNumber()),
                new PreprocessingRechargeMoney.TranAmt(command.getRechargeAmount()),
                new PreprocessingRechargeMoney.UserSqlNo("사용자 일련번호"),
                new PreprocessingRechargeMoney.TranDtime(LocalDateTime.now()),
                new PreprocessingRechargeMoney.ReqClientName(command.getMemberName()),
                new PreprocessingRechargeMoney.ReqClientNum(command.getMemberName() + command.getLinkedBankAccountNumber() + "회원번호"),
                new PreprocessingRechargeMoney.TransferPurpose(TransferPurposeType.RECHARGE)
        );
    }

    private static String createRandomNumber() {
        return UUID.randomUUID().toString();
    }

}

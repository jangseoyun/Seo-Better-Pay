package com.yun.openbanking.adapter.in.factory;

import com.yun.openbanking.adapter.in.web.model.RechargeTransferWithdrawRequest;
import com.yun.openbanking.application.port.in.RechargeTransferWithdrawCommand;
import com.yun.openbanking.domain.TransferWithdraw;

public class RechargeTransferWithdrawFactory {
    public static RechargeTransferWithdrawCommand newInstance(RechargeTransferWithdrawRequest request) {
        return RechargeTransferWithdrawCommand.builder()
                .bankTranId(request.bankTranId())
                .cntrAccountType(request.cntrAccountType())
                .cntrAccountNum(request.cntrAccountNum())
                .dpsPrintContent(request.dpsPrintContent())
                .wdBankCodeStd(request.wdBankCodeStd())
                .wdAccountNum(request.wdAccountNum())
                .tranAmt(request.tranAmt())
                .userSeqNo(request.userSeqNo())
                .tranDtime(request.tranDtime())
                .reqClientName(request.reqClientName())
                .reqClientNum(request.reqClientNum())
                .transferPurpose(request.transferPurpose())
                .build();
    }

    public static TransferWithdraw of(RechargeTransferWithdrawCommand command) {
        return TransferWithdraw.generatedTransferWithdraw(
                new TransferWithdraw.BankTranId(command.getBankTranId()),
                new TransferWithdraw.CntrAccountType(command.getCntrAccountType()),
                new TransferWithdraw.CntrAccountNum(command.getCntrAccountNum()),
                new TransferWithdraw.DpsPrintContent(command.getDpsPrintContent()),
                new TransferWithdraw.WdBankCodeStd(command.getWdBankCodeStd()),
                new TransferWithdraw.WdAccountNum(command.getWdAccountNum()),
                new TransferWithdraw.TranAmt(command.getTranAmt()),
                new TransferWithdraw.UserSeqNo(command.getUserSeqNo()),
                new TransferWithdraw.TranDtime(command.getTranDtime()),
                new TransferWithdraw.ReqClientName(command.getReqClientName()),
                new TransferWithdraw.ReqClientNum(command.getReqClientNum()),
                new TransferWithdraw.TransferPurpose(command.getTransferPurpose())
        );
    }

}

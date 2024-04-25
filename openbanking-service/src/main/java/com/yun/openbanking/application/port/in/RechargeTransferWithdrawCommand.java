package com.yun.openbanking.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.openbanking.adapter.in.web.model.RechargeTransferWithdrawRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RechargeTransferWithdrawCommand extends SelfValidating<RechargeTransferWithdrawRequest> {
    private String bankTranId;
    private String cntrAccountType;
    private String cntrAccountNum;
    private String dpsPrintContent;
    private String wdBankCodeStd;
    private String wdAccountNum;
    private Integer tranAmt;
    private String userSeqNo;
    private Integer tranDtime;
    private String reqClientName;
    private String reqClientNum;
    private String transferPurpose;

    @Builder
    public RechargeTransferWithdrawCommand(String bankTranId,
                                           String cntrAccountType,
                                           String cntrAccountNum,
                                           String dpsPrintContent,
                                           String wdBankCodeStd,
                                           String wdAccountNum,
                                           Integer tranAmt,
                                           String userSeqNo,
                                           Integer tranDtime,
                                           String reqClientName,
                                           String reqClientNum,
                                           String transferPurpose) {
        this.bankTranId = bankTranId;
        this.cntrAccountType = cntrAccountType;
        this.cntrAccountNum = cntrAccountNum;
        this.dpsPrintContent = dpsPrintContent;
        this.wdBankCodeStd = wdBankCodeStd;
        this.wdAccountNum = wdAccountNum;
        this.tranAmt = tranAmt;
        this.userSeqNo = userSeqNo;
        this.tranDtime = tranDtime;
        this.reqClientName = reqClientName;
        this.reqClientNum = reqClientNum;
        this.transferPurpose = transferPurpose;
        this.validateSelf();
    }

}

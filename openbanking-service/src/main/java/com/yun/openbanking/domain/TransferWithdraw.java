package com.yun.openbanking.domain;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferWithdraw {
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

    public static TransferWithdraw generatedTransferWithdraw(
                                    BankTranId bankTranId,
                                    CntrAccountType cntrAccountType,
                                    CntrAccountNum cntrAccountNum,
                                    DpsPrintContent dpsPrintContent,
                                    WdBankCodeStd wdBankCodeStd,
                                    WdAccountNum wdAccountNum,
                                    TranAmt tranAmt,
                                    UserSeqNo userSeqNo,
                                    TranDtime tranDtime,
                                    ReqClientName reqClientName,
                                    ReqClientNum reqClientNum,
                                    TransferPurpose transferPurpose
    ) {
        return new TransferWithdraw(
                bankTranId.bankTranId,
                cntrAccountType.cntrAccountType,
                cntrAccountNum.cntrAccountNum,
                dpsPrintContent.dpsPrintContent,
                wdBankCodeStd.wdBankCodeStd,
                wdAccountNum.wdAccountNum,
                tranAmt.tranAmt,
                userSeqNo.userSeqNo,
                tranDtime.tranDtime,
                reqClientName.reqClientName,
                reqClientNum.reqClientNum,
                transferPurpose.transferPurpose);
    }

    @Value
    public static class BankTranId {
        String bankTranId;

        public BankTranId(String value) {
            this.bankTranId = value;
        }
    }

    @Value
    public static class CntrAccountType {
        String cntrAccountType;

        public CntrAccountType(String value) {
            this.cntrAccountType = value;
        }
    }

    @Value
    public static class CntrAccountNum {
        String cntrAccountNum;

        public CntrAccountNum(String value) {
            this.cntrAccountNum = value;
        }
    }

    @Value
    public static class DpsPrintContent {
        String dpsPrintContent;

        public DpsPrintContent(String value) {
            this.dpsPrintContent = value;
        }
    }

    @Value
    public static class WdBankCodeStd {
        String wdBankCodeStd;

        public WdBankCodeStd(String value) {
            this.wdBankCodeStd = value;
        }
    }

    @Value
    public static class WdAccountNum {
        String wdAccountNum;

        public WdAccountNum(String value) {
            this.wdAccountNum = value;
        }
    }

    @Value
    public static class TranAmt {
        Integer tranAmt;

        public TranAmt(Integer value) {
            this.tranAmt = value;
        }
    }

    @Value
    public static class UserSeqNo {
        String userSeqNo;

        public UserSeqNo(String value) {
            this.userSeqNo = value;
        }
    }

    @Value
    public static class TranDtime {
        Integer tranDtime;

        public TranDtime(Integer value) {
            this.tranDtime = value;
        }
    }

    @Value
    public static class ReqClientName {
        String reqClientName;

        public ReqClientName(String value) {
            this.reqClientName = value;
        }
    }

    @Value
    public static class ReqClientNum {
        String reqClientNum;

        public ReqClientNum(String value) {
            this.reqClientNum = value;
        }
    }

    @Value
    public static class TransferPurpose {
        String transferPurpose;

        public TransferPurpose(String value) {
            this.transferPurpose = value;
        }
    }

}

package com.yun.money.domain;

import com.yun.money.application.port.factory.CompanyName;
import com.yun.money.application.port.factory.AgreementAccountType;
import com.yun.money.application.port.factory.TransferPurposeType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * 오픈뱅킹 API 출금 이체 API 요청 데이터 전처리 domain
 * bankTranId:      거래고유번호(참가기관)
 * cntrAccountType: 약정 구분 “N”:계좌, “C”:계정
 * cntrAccountNum:  약정 계좌/계정 번호
 * dpsPrintContent: 입금계좌인자내역
 * wdBankCodeStd:   출금은행.표준코드
 * wdAccountNum:    출금계좌번호
 * tranAmt:         거래금액
 * userSqlNo:       사용자일련번호
 * tranDtime:       요청일시
 * reqClientName:   요청고객성명
 * reqClientNum:    요청고객회원번호
 * transferPurpose: 이체용도
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PreprocessingRechargeMoney {

    private final String bankTranId;
    private final AgreementAccountType cntrAccountType;
    private final String cntrAccountNum;
    private final CompanyName dpsPrintContent;
    private final String wdBankCodeStd;
    private final String wdAccountNum;
    private final int tranAmt;
    private final String userSqlNo;
    private final LocalDateTime tranDtime;
    private final String reqClientName;
    private final String reqClientNum;
    private final TransferPurposeType transferPurpose;

    public static PreprocessingRechargeMoney generatedRechargeMoney(
            BankTranId bankTranId,
            CntrAccountType cntrAccountType,
            CntrAccountNum cntrAccountNum,
            DpsPrintContent dpsPrintContent,
            WdBankCodeStd wdBankCodeStd,
            WdAccountNum wdAccountNum,
            TranAmt tranAmt,
            UserSqlNo userSqlNo,
            TranDtime tranDtime,
            ReqClientName reqClientName,
            ReqClientNum reqClientNum,
            TransferPurpose transferPurpose
    ) {
        return new PreprocessingRechargeMoney(
                bankTranId.id,
                cntrAccountType.type,
                cntrAccountNum.number,
                dpsPrintContent.companyPrint,
                wdBankCodeStd.code,
                wdAccountNum.number,
                tranAmt.amount,
                userSqlNo.no,
                tranDtime.date,
                reqClientName.name,
                reqClientNum.number,
                transferPurpose.purpose
        );
    }

    @Value
    public static class BankTranId {
        String id;

        public BankTranId(String value) {
            this.id = value;
        }
    }

    @Value
    public static class CntrAccountType {
        AgreementAccountType type;

        public CntrAccountType(AgreementAccountType value) {
            this.type = value;
        }
    }

    @Value
    public static class CntrAccountNum {
        String number;

        public CntrAccountNum(String value) {
            this.number = value;
        }
    }

    @Value
    public static class DpsPrintContent {
        CompanyName companyPrint;

        public DpsPrintContent(CompanyName value) {
            this.companyPrint = value;
        }
    }

    @Value
    public static class WdBankCodeStd {
        String code;

        public WdBankCodeStd(String value) {
            this.code = value;
        }
    }

    @Value
    public static class WdAccountNum {
        String number;

        public WdAccountNum(String value) {
            this.number = value;
        }
    }

    @Value
    public static class TranAmt {
        int amount;

        public TranAmt(int value) {
            this.amount = value;
        }
    }

    @Value
    public static class UserSqlNo {
        String no;

        public UserSqlNo(String value) {
            this.no = value;
        }
    }

    @Value
    public static class TranDtime {
        LocalDateTime date;

        public TranDtime(LocalDateTime value) {
            this.date = value;
        }
    }

    @Value
    public static class ReqClientName {
        String name;

        public ReqClientName(String value) {
            this.name = value;
        }
    }

    @Value
    public static class ReqClientNum {
        String number;

        public ReqClientNum(String value) {
            this.number = value;
        }
    }

    @Value
    public static class TransferPurpose {
        TransferPurposeType purpose;

        public TransferPurpose(TransferPurposeType value) {
            this.purpose = value;
        }
    }

}

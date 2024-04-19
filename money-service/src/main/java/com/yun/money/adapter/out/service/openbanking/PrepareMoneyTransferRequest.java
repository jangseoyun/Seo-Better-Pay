package com.yun.money.adapter.out.service.openbanking;

import lombok.Builder;

/**
 * 출금 이체 API: 계좌번호 사용 (자체인증 기관)
 * @param bankTranId:      (필수)거래고유번호(참가기관)
 * @param cntrAccountType: (필수)약정 계좌/계정구분 “N”:계좌
 * @param cntrAccountNum:  (필수)약정 계좌/계정 번호
 * @param dpsPrintContent: (필수)입금계좌인자내역
 * @param wdBankCodeStd:   (필수)출금은행.표준코드
 * @param wdAccountNum:    (필수)출금계좌번호
 * @param tranAmt:         (필수)거래금액
 * @param userSeqNo:       (필수)사용자일련번호
 * @param tranDtime:       (필수)요청일시
 * @param reqClientName:   (필수)요청고객성명
 * @param reqClientNum:    (필수)요청고객회원번호
 * @param transferPurpose: (필수)이체용도
 */
public record PrepareMoneyTransferRequest(
        String bankTranId,
        String cntrAccountType,
        String cntrAccountNum,
        String dpsPrintContent,
        String wdBankCodeStd,
        String wdAccountNum,
        Integer tranAmt,
        String userSeqNo,
        String tranDtime,
        String reqClientName,
        String reqClientNum,
        String transferPurpose
) {

    @Builder
    public PrepareMoneyTransferRequest(String bankTranId,
                                       String cntrAccountType,
                                       String cntrAccountNum,
                                       String dpsPrintContent,
                                       String wdBankCodeStd,
                                       String wdAccountNum,
                                       Integer tranAmt,
                                       String userSeqNo,
                                       String tranDtime,
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
    }

}

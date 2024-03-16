package com.yun.wiretransferservice.application.service;

import com.yun.common.UseCase;
import com.yun.wiretransferservice.adapter.in.web.model.WireTransferType;
import com.yun.wiretransferservice.application.port.in.WireTransferCommand;
import com.yun.wiretransferservice.application.port.in.WireTransferUseCase;
import com.yun.wiretransferservice.application.port.out.WireTransferRequestPort;
import com.yun.wiretransferservice.application.port.out.banking.BankingForWireTransferPort;
import com.yun.wiretransferservice.application.port.out.membership.MembershipForWireTransferPort;
import com.yun.wiretransferservice.application.port.out.WireTransferPort;
import com.yun.wiretransferservice.application.port.out.membership.MembershipStatus;
import com.yun.wiretransferservice.application.port.out.money.MoneyForWireTransferPort;
import com.yun.wiretransferservice.application.port.out.money.MoneyInfo;
import com.yun.wiretransferservice.domain.WireTransfer;
import com.yun.wiretransferservice.domain.WireTransferRequestDomain;
import com.yun.wiretransferservice.domain.WireTransferRequestStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class WireTransferService implements WireTransferUseCase {

    private final WireTransferRequestPort wireTransferRequestPort;
    private final WireTransferPort wireTransferPort;
    private final MembershipForWireTransferPort membershipForWireTransferPort;
    private final MoneyForWireTransferPort moneyForWireTransferPort;
    private final BankingForWireTransferPort bankingForWireTransferPort;

    @Override
    public WireTransfer requestWireTransfer(WireTransferCommand command) {
        //송금 요청 시작 상태로 기록
        WireTransferRequestDomain successWireTransfer = wireTransferRequestPort.createWireTransferHistory(command.toDomain());

        //1. from member 확인 (membership svc)
        MembershipStatus membershipStatus = membershipForWireTransferPort.getMembershipStatus(command.getFromMembershipId());
        if (membershipStatus.isValid()) {
            return null;
        }

        //2. 잔액 확인 (money svc)
        MoneyInfo moneyInfo = moneyForWireTransferPort.getMoneyInfo(command.getFromMembershipId());
        //2-1. 잔액이 부족한 경우 충전 요청
        if (moneyInfo.moneyTotalBalance() < command.getWireTransferAmount()) {
            //만원 단위로 올림하는 Math 함수
            int rechargingAmount = (int) Math.ceil((command.getWireTransferAmount() - moneyInfo.moneyTotalBalance()) / 10000.0) * 10000;
            boolean moneyResult = moneyForWireTransferPort.requestRechargingMoney(command.getFromMembershipId(), rechargingAmount);

            if (!moneyResult) {return null;}
        }

        //3. wire transfer type 확인(고객/외부 은행)
        //3-1. api 외부 은행일 경우 은행 계좌 정상 여부 확인 & 법인 계좌 -> 외부 은행 송금
        if (command.getWireTransferType() == WireTransferType.EXTERNAL_BANK) {
            boolean bankingResult = bankingForWireTransferPort.requestFirmBanking(command.getToBankName(), command.getToBankAccountNumber(), command.getWireTransferAmount());
            if (!bankingResult) {return null;}
        }

        //3-2. 내부 계좌 송금일 경우  from -> to 계좌 금액 변경
        if (command.getWireTransferType() == WireTransferType.SEOBETTERPAY_MEMBER) {
            //from 고객 머니 감액, to 고객 머니 증액
            boolean decreaseMoney = moneyForWireTransferPort.requestDecreaseMoney(command.getFromMembershipId(), command.getWireTransferAmount());
            boolean increaseMoney = moneyForWireTransferPort.requestIncreaseMoney(command.getToMembershipId(), command.getWireTransferAmount());
            if (!decreaseMoney || !increaseMoney) {return null;}
        }

        //4. 송금 요청 성공 기록
        if (successWireTransfer.getWireTransferRequestStatus() == WireTransferRequestStatus.FAIL) {
            return null;
        }

        return wireTransferPort.saveFinalWireTransfer(successWireTransfer);
    }
}

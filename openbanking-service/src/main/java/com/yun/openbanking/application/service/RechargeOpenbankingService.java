package com.yun.openbanking.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.openbanking.adapter.in.factory.RechargeTransferWithdrawFactory;
import com.yun.openbanking.application.port.in.RechargeTransferWithdrawCommand;
import com.yun.openbanking.application.port.in.RechargeTransferWithdrawUseCase;
import com.yun.openbanking.application.port.out.TransferWithdrawPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class RechargeOpenbankingService implements RechargeTransferWithdrawUseCase {

    private final TransferWithdrawPort transferWithdrawPort;
    @Override
    public void requestTransferWithdrawApi(RechargeTransferWithdrawCommand command) {
        //계좌이체
        transferWithdrawPort.callTransferWithdrawOpenbanking(RechargeTransferWithdrawFactory.of(command));
    }
}

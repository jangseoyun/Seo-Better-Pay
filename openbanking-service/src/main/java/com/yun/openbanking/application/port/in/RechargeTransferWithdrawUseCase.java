package com.yun.openbanking.application.port.in;

public interface RechargeTransferWithdrawUseCase {
    void requestTransferWithdrawApi(RechargeTransferWithdrawCommand command);
}

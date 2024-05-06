package com.yun.openbanking.application.port.out;

import com.yun.openbanking.domain.TransferWithdraw;

public interface TransferWithdrawPort {
    void callTransferWithdrawOpenbanking(TransferWithdraw transferWithdraw);
}

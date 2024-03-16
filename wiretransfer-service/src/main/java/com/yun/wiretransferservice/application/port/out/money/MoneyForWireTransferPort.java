package com.yun.wiretransferservice.application.port.out.money;

public interface MoneyForWireTransferPort {
    MoneyInfo getMoneyInfo(String membershipId);
    boolean requestRechargingMoney(String membershipId, int amount);
    boolean requestIncreaseMoney(String membershipId, int amount);
    boolean requestDecreaseMoney(String membershipId, int amount);
}

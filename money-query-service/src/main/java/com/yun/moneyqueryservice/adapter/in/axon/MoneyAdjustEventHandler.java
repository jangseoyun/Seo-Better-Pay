package com.yun.moneyqueryservice.adapter.in.axon;

import com.yun.moneyqueryservice.application.port.out.GetMemberAddressInfoPort;
import com.yun.moneyqueryservice.application.port.out.InsertMoneyIncreaseEventByAddress;
import com.yun.moneyqueryservice.application.port.out.MemberAddressInfo;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MoneyAdjustEventHandler {
    @EventHandler
    public void handler(RequestFirmbankingFinishedEvent event
            , GetMemberAddressInfoPort getMemberAddressInfoPort
            , InsertMoneyIncreaseEventByAddress insertMoneyIncreaseEventByAddress) {
        log.info("money adjust event receiced : {}", event.toString());

        //고객 주소 정보
        MemberAddressInfo memberAddressInfo = getMemberAddressInfoPort.getMemberAddressInfo(event.getMembershipId());

        //dynamoDB insert
        String address = memberAddressInfo.address();
        int moneyAmount = event.getMoneyAmount();
        log.info("dynamo insert: {}, {}", address, moneyAmount);

        insertMoneyIncreaseEventByAddress.insertMoneyIncreaseEventByAddress(address, moneyAmount);
    }
}

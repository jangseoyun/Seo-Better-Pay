package com.yun.money.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.money.adapter.in.web.model.RechargeMoneyAmountRequest;
import com.yun.money.adapter.in.web.model.MembershipIdRequest;
import com.yun.money.application.port.factory.RechargeCommandFactory;
import com.yun.money.application.port.in.RechargeMoneyUseCase;
import com.yun.money.application.port.in.ReadMoneyAmountUseCase;
import com.yun.money.domain.MemberMoneyWallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/money")
public class RequestMoneyAdjustController {

    private final ReadMoneyAmountUseCase readMoneyAmountUseCase;
    private final RechargeMoneyUseCase rechargeMoneyUseCase;

    @PostMapping("/{membershipId}/increase-eda")
    public void increaseMoneyAdjustingRequestByEvent(@PathVariable("membershipId") MembershipIdRequest requestMember,
                                                     @RequestBody RechargeMoneyAmountRequest request) {
        MemberMoneyWallet memberMoneyWallet = readMoneyAmountUseCase.getMemberMoneyWallet(requestMember.toCommand());
        rechargeMoneyUseCase.addMoneyToSeobetterpayByEvent(memberMoneyWallet, RechargeCommandFactory.newInstance(request));
    }
}

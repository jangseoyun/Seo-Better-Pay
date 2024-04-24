package com.yun.openbanking.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.openbanking.adapter.in.factory.RechargeTransferWithdrawFactory;
import com.yun.openbanking.adapter.in.web.model.RechargeTransferWithdrawRequest;
import com.yun.openbanking.application.port.in.RechargeTransferWithdrawUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/openbanking")
public class RechargeTransferWithdrawController {

    private final RechargeTransferWithdrawUseCase rechargeTransferWithdrawUseCase;

    //TODO: spring security 커스텀
    @PostMapping("/transfer-withdraw")
    public String requestRechargeTransferWithdraw(@RequestBody RechargeTransferWithdrawRequest request) {
        //사용자 인증에 대한 request3AuthorizeMember 인증은 캐싱을 통해 저장한 뒤 꺼내서 사용한다
        rechargeTransferWithdrawUseCase.requestTransferWithdrawApi(RechargeTransferWithdrawFactory.newInstance(request));
        return "";
    }
}

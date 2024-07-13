package com.yun.money.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.money.adapter.in.web.factory.MembershipMoneyFactory;
import com.yun.money.adapter.in.web.model.MembershipIdRequest;
import com.yun.money.adapter.in.web.model.MembershipListRequest;
import com.yun.money.application.port.in.ReadMoneyAmountUseCase;
import com.yun.money.domain.MemberMoneyWallet;
import com.yun.money.domain.PayWalletMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * getMoneyTotalAmount() : member의 money 잔액 조회
 * getAddMoneyHistory() :  member의 충전 히스토리 조회
 * getPayMoneyHistory() : member의 사용 히스토리 조회
 */
@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/money")
public class ReadMoneyAmountController{

    private final ReadMoneyAmountUseCase readMoneyAmountUseCase;

    @GetMapping("/{membershipId}/amount")
    public ResponseEntity getMoneyTotalAmount(@PathVariable("membershipId") MembershipIdRequest membershipId) {
        Integer moneyTotalAmount = readMoneyAmountUseCase.getMoneyTotalAmount(membershipId.toCommand());
        return ResponseEntity.ok().body(moneyTotalAmount);
    }

    @GetMapping("/{membershipId}/add-history")
    public ResponseEntity getAddMoneyHistory(@PathVariable("membershipId") MembershipIdRequest membershipId) {
        List<PayWalletMoney> addMoneyHistory = readMoneyAmountUseCase.getAddMoneyHistory(membershipId.toCommand());
        return ResponseEntity.ok().body(addMoneyHistory);
    }

    @GetMapping("/{membershipId}/pay-history")
    public ResponseEntity getPayMoneyHistory(@PathVariable("membershipId") MembershipIdRequest membershipId) {
        List<PayWalletMoney> payMoneyHistory = readMoneyAmountUseCase.getPayMoneyHistory(membershipId.toCommand());
        return ResponseEntity.ok().body(payMoneyHistory);
    }

    @PostMapping("/membership")
    public ResponseEntity getMembershipMoneyListByIds(@RequestBody MembershipListRequest membershipListRequest) {
        List<MemberMoneyWallet> membershipMoneyList = readMoneyAmountUseCase.getMembershipMoneyListById(
                MembershipMoneyFactory.newMembershipIdListCommand(membershipListRequest)
        );
        log.info("getMembershipMoneyListByIds response: {}", membershipMoneyList);
        return ResponseEntity.ok().body(membershipMoneyList);
    }
}

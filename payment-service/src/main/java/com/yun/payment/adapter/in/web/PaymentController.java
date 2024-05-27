package com.yun.payment.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import com.yun.payment.adapter.in.web.factory.PaymentCreateFactory;
import com.yun.payment.adapter.in.web.model.MembershipIdRequest;
import com.yun.payment.adapter.in.web.model.MembershipPaymentRequest;
import com.yun.payment.adapter.in.web.model.UpdatePaymentStatusToSettledRequest;
import com.yun.payment.application.port.in.PaymentCommandUseCase;
import com.yun.payment.domain.PaymentRequestInfo;
import com.yun.payment.domain.PaymentSettledSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentCommandUseCase paymentCommandUseCase;

    @PostMapping("/request")
    public ResponseEntity requestPayment(@RequestBody MembershipPaymentRequest membershipPaymentRequest) {
        log.info("request payment: {}", membershipPaymentRequest);
        //결제 요청
        PaymentRequestInfo paymentRequestInfo
                = paymentCommandUseCase.requestPayment(PaymentCreateFactory.newInstanceCommand(membershipPaymentRequest));
        //결제 요청 기록
        return ResponseEntity.ok().body(paymentRequestInfo);
    }

    //정상 결제 건들의 list를 얻어오기 위한 API 필요
    @GetMapping("/success-payment")
    public ResponseEntity getSuccessPaymentList() {
        List<PaymentRequestInfo> successPaymentList = paymentCommandUseCase.getSuccessPaymentList();
        return ResponseEntity.ok().body(successPaymentList);
    }

    @GetMapping("/success-payment/{membershipId}")
    public ResponseEntity getSuccessPaymentList(@PathVariable("membershipId") MembershipIdRequest membershipIdRequest) {
        List<PaymentRequestInfo> successPaymentList = paymentCommandUseCase.getSuccessPaymentsByMembershipId(PaymentCreateFactory.newInstanceCommand(membershipIdRequest));
        return ResponseEntity.ok().body(successPaymentList);
    }

    //각 결제 건들의 상태를 변경하기 위한 API 필요
    @PostMapping("/settlement")
    public ResponseEntity updatePaymentStatusToSettled(@RequestBody UpdatePaymentStatusToSettledRequest request) {
        log.info("updatePaymentStatusToSettled request: {}", request);
        PaymentSettledSuccessResponse paymentSettledSuccessResponse
                = paymentCommandUseCase.finalizePaymentSettlement(PaymentCreateFactory.newInstanceCommand(request));
        return ResponseEntity.ok().body(paymentSettledSuccessResponse);
    }
}

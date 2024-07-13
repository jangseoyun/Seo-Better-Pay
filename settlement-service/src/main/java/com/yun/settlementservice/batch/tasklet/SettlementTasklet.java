package com.yun.settlementservice.batch.tasklet;

import com.yun.settlementservice.adapter.out.service.payment.PaymentRequestInfo;
import com.yun.settlementservice.application.port.out.SettlementBankingPort;
import com.yun.settlementservice.application.port.out.SettlementPaymentPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SettlementTasklet implements Tasklet {

    private final SettlementPaymentPort settlementPaymentPort;
    private final SettlementBankingPort settlementBankingPort;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        //비즈니스 로직
        // payment
        // 1. 정상 결제건들 + membershipId(프렌차이즈) 조회 payment-result-receipt
        PaymentRequestInfo.MembershipId id1 = new PaymentRequestInfo.MembershipId("id1");
        List<PaymentRequestInfo> paymentRequestInfos = settlementPaymentPort.sendPaymentSuccessByMembershipId(id1);
        log.info("SettlementTasklet : {}", paymentRequestInfos);

        // 각 프렌차이즈아이디 별로 정산 금액 계산

        // banking
        // 1. 등록된 계좌 정보 가져오기
        // 2. 정산 금액만큼 계좌에 입금 요청 (펌 뱅킹) - 정산

        // payment
        // 2. 특정 결제 건에 대해서 상태값을 정산 완료로 변경

        return RepeatStatus.FINISHED;
    }
}

package com.yun.payment.adapter.out.persistance;

import com.yun.common.anotation.PersistenceAdapter;
import com.yun.payment.application.factory.PaymentApplicationFactory;
import com.yun.payment.application.port.out.PaymentOperatePort;
import com.yun.payment.domain.PaymentRequestInfo;
import com.yun.payment.domain.PaymentSettledSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.yun.payment.domain.PaymentSuccessOrderIds.PaymentOrderIdList;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
@Transactional
public class PaymentAdapter implements PaymentOperatePort {

    private final PaymentMapper mapper;
    private final PaymentRequestEventRepository paymentRequestEventRepository;
    private final PaymentResultReceiptRepository paymentResultReceiptRepository;


    @Override
    public PaymentRequestInfo paymentEventRecord(PaymentRequestInfo paymentRequestInfo) {
        PaymentRequestEventEntity requestEventEntity = paymentRequestEventRepository.save(mapper.toEntity(paymentRequestInfo));
        return mapper.toPaymentRequestInfo(requestEventEntity);
    }

    @Override
    public PaymentRequestInfo exportRequestPayment(PaymentRequestInfo paymentRequestInfo) {
        //TODO: 실패 기록을 어떻게 남길것인가?
        PaymentResultReceiptEntity entity = paymentResultReceiptRepository.save(mapper.toReceiptEntity(paymentRequestInfo));
        return mapper.toPaymentRequestInfo(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PaymentRequestInfo> paymentSuccessList() {
        return paymentResultReceiptRepository.findAllBySuccess(PaymentStatus.SUCCESS).stream()
                .map(entity -> mapper.toPaymentRequestInfo(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentRequestInfo> paymentSuccessByMembershipId(PaymentRequestInfo.MembershipId membershipId) {
        return paymentResultReceiptRepository.findAllSuccessPaymentByMembershipId(membershipId.getMembershipId()).stream()
                .map(entity -> mapper.toPaymentRequestInfo(entity))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentSettledSuccessResponse markPaymentAsSettled(PaymentOrderIdList paymentOrderIdList) {
        paymentResultReceiptRepository.updatePaymentStatusToSettled(
                paymentOrderIdList.getPaymentOrderIdList(), PaymentStatus.SETTLED
        );

        return PaymentApplicationFactory.newSuccessResponse();
    }
}

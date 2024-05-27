package com.yun.payment.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentResultReceiptRepository extends JpaRepository<PaymentResultReceiptEntity, String> {
    @Query(value = "select table from PaymentResultReceiptEntity table where table.paymentStatus = :status")
    List<PaymentResultReceiptEntity> findAllBySuccess(@Param("status") PaymentStatus status);

    @Modifying
    @Query(value = "UPDATE PaymentResultReceiptEntity result SET result.paymentStatus = :status WHERE result.paymentOrderId IN :paymentOrderIds")
    void updatePaymentStatusToSettled(
            @Param("paymentOrderIds") List<String> paymentOrderIds,
            @Param("status") PaymentStatus status
    );

    @Query(value = "select result from PaymentResultReceiptEntity result where result.membershipId = :membershipId")
    List<PaymentResultReceiptEntity> findAllSuccessPaymentByMembershipId(@Param("membershipId") String membershipId);
}

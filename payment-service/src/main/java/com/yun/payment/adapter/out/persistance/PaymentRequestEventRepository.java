package com.yun.payment.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRequestEventRepository extends JpaRepository<PaymentRequestEventEntity, Long> {
}

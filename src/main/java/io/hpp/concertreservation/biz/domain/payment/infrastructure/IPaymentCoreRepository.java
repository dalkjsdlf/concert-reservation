package io.hpp.concertreservation.biz.domain.payment.infrastructure;

import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentCoreRepository extends JpaRepository<Payment, Long> {
}

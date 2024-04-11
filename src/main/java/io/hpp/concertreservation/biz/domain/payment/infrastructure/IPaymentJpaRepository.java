package io.hpp.concertreservation.biz.domain.payment.infrastructure;

import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentJpaRepository extends JpaRepository<Payment, Long> {
}

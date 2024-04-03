package io.hpp.concertreservation.biz.domain.userpayment.repository;

import io.hpp.concertreservation.biz.domain.userpayment.model.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentCoreRepository extends JpaRepository<UserPayment, Long> {
}

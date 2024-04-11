package io.hpp.concertreservation.biz.domain.payment.repository;

import io.hpp.concertreservation.biz.domain.payment.model.Payment;

import java.util.List;
import java.util.Optional;

public interface IPaymentLoadRepository {

    List<Payment> findByUserId(Long userId);
}

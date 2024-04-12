package io.hpp.concertreservation.biz.domain.payment.repository;


import io.hpp.concertreservation.biz.domain.payment.model.Payment;

public interface IPaymentStoreRepository {
    public Payment savePayment(Payment payment);
}

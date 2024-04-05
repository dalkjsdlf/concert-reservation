package io.hpp.concertreservation.biz.domain.payment.repository;

import io.hpp.concertreservation.biz.domain.paymoney.model.UserPayment;

public interface IUserPaymentWriter {
    UserPayment writeUserPayment(UserPayment userPayment);
}

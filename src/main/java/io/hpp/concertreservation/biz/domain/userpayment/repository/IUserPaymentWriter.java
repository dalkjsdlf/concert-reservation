package io.hpp.concertreservation.biz.domain.userpayment.repository;

import io.hpp.concertreservation.biz.domain.userpayment.model.UserPayment;

public interface IUserPaymentWriter {
    UserPayment writeUserPayment(UserPayment userPayment);
}

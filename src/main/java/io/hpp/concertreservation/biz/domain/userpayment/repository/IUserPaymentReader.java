package io.hpp.concertreservation.biz.domain.userpayment.repository;

import io.hpp.concertreservation.biz.domain.userpayment.enumclass.PayMethod;
import io.hpp.concertreservation.biz.domain.userpayment.model.UserPayment;

import java.util.List;
import java.util.Optional;

public interface IUserPaymentReader {

    Optional<UserPayment> readById(Long userPaymentId);
    Optional<UserPayment> readByUserIdAndPayMethod(Long userId, PayMethod payMethod);
    List<UserPayment> readByUserId(Long userId);
}

package io.hpp.concertreservation.biz.domain.paymoney.repository;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;

import java.util.Optional;


public interface IPayMoneyLoadRepository {

    Optional<PayMoney> findById(Long userPaymentId);
    Optional<PayMoney> findByUserIdAndPayMethod(Long userId, PayMethod payMethod);

    //List<PayMoney> readByUserId(Long userId);

}

package io.hpp.concertreservation.biz.domain.paymoney.repository;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;

import java.util.Optional;


public interface IPayMoneyLoadRepository {

    Optional<PayMoney> readById(Long userPaymentId);
    Optional<PayMoney> readByUserIdAndPayMethod(Long userId, PayMethod payMethod);

    //List<PayMoney> readByUserId(Long userId);

}

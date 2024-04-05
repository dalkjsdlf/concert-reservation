package io.hpp.concertreservation.biz.domain.paymoney.repository;

import io.hpp.concertreservation.biz.domain.paymoney.enumclass.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import org.hibernate.mapping.List;

import java.util.Optional;


public interface IPayMoneyReader {

    Optional<PayMoney> readById(Long userPaymentId);
    Optional<PayMoney> readByUserIdAndPayMethod(Long userId, PayMethod payMethod);

    //List<PayMoney> readByUserId(Long userId);

}

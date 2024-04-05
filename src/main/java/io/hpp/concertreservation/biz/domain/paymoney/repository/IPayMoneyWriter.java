package io.hpp.concertreservation.biz.domain.paymoney.repository;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;

public interface IPayMoneyWriter {
    PayMoney writeUserPayment(PayMoney userPayment);
}

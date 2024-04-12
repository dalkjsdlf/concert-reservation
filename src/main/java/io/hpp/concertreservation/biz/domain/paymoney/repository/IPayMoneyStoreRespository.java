package io.hpp.concertreservation.biz.domain.paymoney.repository;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;

public interface IPayMoneyStoreRespository {
    PayMoney savePayMoney(PayMoney payMoney);
}

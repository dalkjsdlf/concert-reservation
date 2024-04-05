package io.hpp.concertreservation.biz.domain.paymoney.service;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyRequestDto;

public interface IPayMoneyService {

    void chargeMoney(PayMoneyRequestDto chargeDto);
}

package io.hpp.concertreservation.biz.domain.payment.service;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyRequestDto;

public interface IPaymentService {

    //금액
    //사용자Id
    //이용수단
    void useMoney(PayMoneyRequestDto useDto);
    void chargeMoney(PayMoneyRequestDto chargeDto);
}

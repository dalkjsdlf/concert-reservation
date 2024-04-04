package io.hpp.concertreservation.biz.domain.userpayment.service;

import io.hpp.concertreservation.biz.api.userpayment.dto.UserPaymentRequestDto;

public interface IUserPaymentService {

    //금액
    //사용자Id
    //이용수단
    void useMoney(UserPaymentRequestDto useDto);
    void chargeMoney(UserPaymentRequestDto chargeDto);
}

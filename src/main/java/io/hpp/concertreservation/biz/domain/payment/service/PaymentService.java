package io.hpp.concertreservation.biz.domain.payment.service;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyRequestDto;
import io.hpp.concertreservation.biz.domain.payment.repository.IPaymentReader;
import io.hpp.concertreservation.biz.domain.payment.repository.IPaymentWriter;
import org.springframework.beans.factory.annotation.Autowired;


public class PaymentService implements IPaymentService {

    private final IPaymentReader paymentReader;

    private final IPaymentWriter paymentWriter;

    public PaymentService(@Autowired IPaymentReader paymentReader,
                          @Autowired IPaymentWriter paymentWriter) {
        this.paymentReader = paymentReader;
        this.paymentWriter = paymentWriter;
    }

    @Override
    public void useMoney(PayMoneyRequestDto useDto) {

    }

    @Override
    public void chargeMoney(PayMoneyRequestDto chargeDto) {

    }
}

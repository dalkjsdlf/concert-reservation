package io.hpp.concertreservation.biz.domain.payment.repository;

import io.hpp.concertreservation.biz.domain.paymoney.infrastructure.IPayMoneyCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentReader implements IPaymentReader {

    IPayMoneyCoreRepository paymentCoreRepository;

    public PaymentReader(@Autowired IPayMoneyCoreRepository paymentCoreRepository) {
        this.paymentCoreRepository = paymentCoreRepository;
    }
}

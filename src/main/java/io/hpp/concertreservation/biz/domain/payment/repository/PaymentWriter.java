package io.hpp.concertreservation.biz.domain.payment.repository;

import io.hpp.concertreservation.biz.domain.paymoney.infrastructure.IPayMoneyCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentWriter implements IPaymentWriter {
    private final IPayMoneyCoreRepository paymentCoreRepository;

    public PaymentWriter(@Autowired IPayMoneyCoreRepository paymentCoreRepository) {
        this.paymentCoreRepository = paymentCoreRepository;
    }

//    @Override
//    public Payment writeUserPayment(Payment payment) {
//        return paymentCoreRepository.save(payment);
//    }
}


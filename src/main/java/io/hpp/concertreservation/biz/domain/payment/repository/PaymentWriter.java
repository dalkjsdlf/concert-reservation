package io.hpp.concertreservation.biz.domain.payment.repository;

import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.paymoney.infrastructure.IPaymoneyCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentWriter implements IPaymentWriter {
    private final IPaymoneyCoreRepository paymentCoreRepository;

    public PaymentWriter(@Autowired IPaymoneyCoreRepository paymentCoreRepository) {
        this.paymentCoreRepository = paymentCoreRepository;
    }

//    @Override
//    public Payment writeUserPayment(Payment payment) {
//        return paymentCoreRepository.save(payment);
//    }
}


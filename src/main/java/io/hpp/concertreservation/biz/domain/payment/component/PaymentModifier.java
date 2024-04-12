package io.hpp.concertreservation.biz.domain.payment.component;

import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.payment.repository.IPaymentStoreRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentModifier {

    private final IPaymentStoreRepository paymentStoreRepository;

    public PaymentModifier(IPaymentStoreRepository paymentStoreRepository) {
        this.paymentStoreRepository = paymentStoreRepository;
    }

    public void addPaymentHistory(Payment payment){
        paymentStoreRepository.savePayment(payment);
    }
}

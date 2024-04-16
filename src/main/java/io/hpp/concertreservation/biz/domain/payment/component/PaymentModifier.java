package io.hpp.concertreservation.biz.domain.payment.component;

import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.payment.repository.IPaymentStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class PaymentModifier {

    private final IPaymentStoreRepository paymentStoreRepository;

    public void addPaymentHistory(Payment payment){
        paymentStoreRepository.savePayment(payment);
    }
}

package io.hpp.concertreservation.biz.domain.payment.infrastructure;

import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.payment.repository.IPaymentStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class PaymentCoreStoreRepository implements IPaymentStoreRepository {

    private final IPaymentJpaRepository paymentJpaRepository;

    public Payment savePayment(Payment payment){
        return paymentJpaRepository.save(payment);
    }
}

package io.hpp.concertreservation.biz.domain.payment.infrastructure;

import io.hpp.concertreservation.biz.domain.payment.repository.IPaymentStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentCoreStoreRepository implements IPaymentStoreRepository {

    private final IPaymentJpaRepository paymentJpaRepository;

    public PaymentCoreStoreRepository(@Autowired IPaymentJpaRepository paymentJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
    }
}

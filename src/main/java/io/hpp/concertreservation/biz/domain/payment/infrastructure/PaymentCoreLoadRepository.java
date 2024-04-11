package io.hpp.concertreservation.biz.domain.payment.infrastructure;

import io.hpp.concertreservation.biz.domain.payment.repository.IPaymentLoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentCoreLoadRepository implements IPaymentLoadRepository {

    private final IPaymentJpaRepository paymentJpaRepository;

    public PaymentCoreLoadRepository(@Autowired IPaymentJpaRepository paymentJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
    }
}

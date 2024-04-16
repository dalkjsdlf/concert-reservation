package io.hpp.concertreservation.biz.domain.payment.infrastructure;

import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.payment.repository.IPaymentLoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Component
public class PaymentCoreLoadRepository implements IPaymentLoadRepository {

    private final IPaymentJpaRepository paymentJpaRepository;

    @Override
    public List<Payment> findByUserId(Long userId) {
        return this.paymentJpaRepository.findByUserId(userId);
    }
}

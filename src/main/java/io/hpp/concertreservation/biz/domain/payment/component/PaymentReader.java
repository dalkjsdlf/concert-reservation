package io.hpp.concertreservation.biz.domain.payment.component;

import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.payment.repository.IPaymentLoadRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PaymentReader {

    private final IPaymentLoadRepository paymentLoadRepository;

    public PaymentReader(IPaymentLoadRepository paymentLoadRepository) {
        this.paymentLoadRepository = paymentLoadRepository;
    }

    public List<Payment> readAllPaymentsOfUserId(Long userId){
        return paymentLoadRepository.findByUserId(userId);
    }
}

package io.hpp.concertreservation.biz.domain.payment.component;

import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.payment.model.TransactionType;

import java.time.LocalDateTime;

public class PaymentCreator {

    public static Payment create(Long userId, TransactionType tranType, Long amount, LocalDateTime tran_date, Long reservationId){
        return Payment.of(userId, tranType, amount, tran_date, reservationId);
    }
}

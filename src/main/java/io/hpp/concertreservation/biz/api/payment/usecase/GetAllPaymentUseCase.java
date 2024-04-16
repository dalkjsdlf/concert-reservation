package io.hpp.concertreservation.biz.api.payment.usecase;

import io.hpp.concertreservation.biz.api.payment.dto.PaymentResponseDto;
import io.hpp.concertreservation.biz.domain.concert.component.ConcertReader;
import io.hpp.concertreservation.biz.domain.payment.component.PaymentReader;
import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationReader;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllPaymentUseCase {

    private final PaymentReader paymentReader;
    private final ReservationReader reservationReader;
    private final ConcertReader concertReader;

    public List<PaymentResponseDto> execute(Long userId){

        List<PaymentResponseDto> paymentResponseDtos = new ArrayList<PaymentResponseDto>();
        List<Payment> payments = paymentReader.readAllPaymentsOfUserId(userId);


        for(Payment payment : payments){
            paymentResponseDtos.add(
                                    PaymentResponseDto.
                                            builder().
                                            reservationid(payment.getReservationId()).
                                            amount(payment.getAmount()).
                                            tranDate(payment.getTranDate()).
                                            build()
            );
        }
        return paymentResponseDtos;
    }
}

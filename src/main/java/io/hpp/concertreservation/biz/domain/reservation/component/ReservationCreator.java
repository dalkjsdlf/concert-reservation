package io.hpp.concertreservation.biz.domain.reservation.component;

import io.hpp.concertreservation.biz.domain.reservation.model.PaymentStatus;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationCreator {

    public static Reservation create(Long userId,
                                     Long scheduleId,
                                     Integer numOfSeats,
                                     Long totalPrice,
                                     PaymentStatus status){
        return Reservation.of(
                userId,
                scheduleId,
                LocalDateTime.now(),
                numOfSeats,
                totalPrice,
                status);
    }
}

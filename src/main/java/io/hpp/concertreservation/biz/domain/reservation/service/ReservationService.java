package io.hpp.concertreservation.biz.domain.reservation.service;

import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.reservation.repository.ReservationReader;
import io.hpp.concertreservation.biz.domain.reservation.repository.ReservationWriter;
import io.hpp.concertreservation.biz.domain.schedule.repository.ScheduleReader;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.repository.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.repository.SeatWriter;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService implements IReservationService{

    private final SeatReader seatReader;

    private final SeatWriter seatWriter;

    private final ReservationReader reservationReader;

    private final ReservationWriter reservationWriter;

    private final ScheduleReader scheduleReader;

    public ReservationService(@Autowired SeatReader seatReader,
                              @Autowired SeatWriter seatWriter,
                              @Autowired ReservationReader reservationReader,
                              @Autowired ReservationWriter reservationWriter) {
        this.seatReader = seatReader;
        this.seatWriter = seatWriter;
        this.reservationReader = reservationReader;
        this.reservationWriter = reservationWriter;
    }

    @Override
    public Reservation reserveConcert(Long scheduleId, Long userId) {


        Long totalPrice = 0L;



        seatIds.stream().forEach(seatId->{
            Optional<Seat> optSeat = seatReader.readSeatBySeatId(seatId);
            Seat seat = optSeat.orElseThrow(()-> new ReservationException(ReservationErrorResult.NO_SEATS));



            seats.add(seat);
        });





        return null;
    }
}

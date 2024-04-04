package io.hpp.concertreservation.biz.domain.reservation.service;

import io.hpp.concertreservation.biz.domain.reservation.enumclass.PaymentStatus;
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

import java.time.LocalDateTime;
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
                              @Autowired ReservationWriter reservationWriter, ScheduleReader scheduleReader) {
        this.seatReader = seatReader;
        this.seatWriter = seatWriter;
        this.reservationReader = reservationReader;
        this.reservationWriter = reservationWriter;
        this.scheduleReader = scheduleReader;
    }

    @Override
    public void reserveConcert(Long scheduleId, Long userId) {

        // 스케쥴 ID로 좌석들조회

        List<Seat> seats = seatReader.readSeatsByScheduleId(scheduleId);

        seats.forEach(seat->{
            if(seat.getReserveId() != null){
                throw new ReservationException(ReservationErrorResult.ALREADY_SEAT_RESERVED);
            }
        });

        if(seats.isEmpty()){
            throw new ReservationException(ReservationErrorResult.NO_SEATS);
        }

        // 총 금액 계산
        Long totalPrice = seats.stream().map(Seat::getPrice).reduce(Long::sum).orElse(0L);

        // 예약 객체 생성
        Reservation newReservation = Reservation.of(
                userId,
                scheduleId,
                LocalDateTime.now(),
                seats.size(),
                totalPrice, PaymentStatus.WAIT);

        Reservation savedReservation = reservationWriter.save(newReservation);

        Long reservationId = savedReservation.getId();
        // 좌석객체 update
        for(Seat seat : seats){
            seat.setReserveId(reservationId);
            seatWriter.writeSeat(seat);
        }
    }

    @Override
    public void cancelReserveConcert(Long reserveId) {
        Optional<Reservation> optReservation = reservationReader.readReservationById(reserveId);
        Reservation reservation = optReservation.orElseThrow(()->new ReservationException(ReservationErrorResult.NO_RESERVATION));

        Long scheduleId = reservation.getScheduleId();

        List<Seat> seats = seatReader.readSeatsByScheduleId(scheduleId);

        seats.forEach(seat->{
            if(seat.getReserveId() == null){
                throw new ReservationException(ReservationErrorResult.NO_SEAT_SELECTED);
            }
        });

        /*
        * 좌석정보의 예약 내역을 null로 지정
        * **/
        seats.forEach(seat->{
            seat.setReserveId(null);
            seatWriter.writeSeat(seat);
        });

        reservationWriter.delete(reserveId);
    }


}

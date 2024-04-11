package io.hpp.concertreservation.biz.domain.reservation.service;

import io.hpp.concertreservation.biz.api.reservation.dto.ReservationResponseDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservationService{

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

    public ReservationResponseDto reserveConcert(Long scheduleId, List<Long> seatIds, Long userId) {

        if(seatIds.isEmpty()){
            throw new ReservationException(ReservationErrorResult.NO_SEAT_INPUT);
        }

        Long cmpScheduleId = 0L;

        List<Seat> seats = new ArrayList<Seat>();
        // 스케쥴 ID로 좌석들조회
        for(Long seatId : seatIds){
            Optional<Seat> optSeat = seatReader.readSeatBySeatId(seatId);
            Seat seat = optSeat.orElseThrow(()->new ReservationException(ReservationErrorResult.NO_SEAT));
            if(seat.getReserveId() != null){
                throw new ReservationException(ReservationErrorResult.ALREADY_SEAT_RESERVED);
            }

            if(cmpScheduleId.compareTo(0L) == 0){
                cmpScheduleId = seat.getScheduleId();
            }else if(cmpScheduleId.compareTo(seat.getScheduleId()) != 0){
                throw new ReservationException(ReservationErrorResult.WRONG_SEAT_INPUT);
            }
            seats.add(seat);
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

        return ReservationResponseDto.
                builder().
                scheduleId(savedReservation.getScheduleId()).
                build();
    }

    public ReservationResponseDto cancelReserveConcert(Long reserveId) {
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

        Reservation deletedReservation = reservationWriter.delete(reserveId);

        return ReservationResponseDto.
                builder().
                scheduleId(deletedReservation.getScheduleId()).
                userId(deletedReservation.getUserId()).
                build();
    }

    public List<ReservationResponseDto> getReservationsByUserId(Long userId){

        List<Reservation> reservations = reservationReader.readReservationsByUserId(userId);

        return reservations.
                stream().
                map(reservation -> ReservationResponseDto.
                        builder().
                        scheduleId(reservation.getScheduleId()).

                        build()).
                collect(Collectors.toList());
    }


    public List<ReservationResponseDto> getReservationsById(Long userId){

        List<Reservation> reservations = reservationReader.readReservationsByUserId(userId);


        return reservations.
                stream().
                map(reservation -> ReservationResponseDto.
                        builder().
                        scheduleId(reservation.getScheduleId()).

                        build()).
                collect(Collectors.toList());
    }

}

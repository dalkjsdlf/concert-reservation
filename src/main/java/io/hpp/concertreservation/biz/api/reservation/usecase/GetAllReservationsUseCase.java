package io.hpp.concertreservation.biz.api.reservation.usecase;

import io.hpp.concertreservation.biz.api.reservation.dto.ReservationResponseDto;
import io.hpp.concertreservation.biz.domain.concert.component.ConcertReader;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationReader;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.schedule.component.ScheduleReader;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.seat.component.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllReservationsUseCase{
    private final ReservationReader reservationReader;
    private final ScheduleReader scheduleReader;
    private final ConcertReader concertReader;
    private final SeatReader seatReader;

    public GetAllReservationsUseCase(ReservationReader reservationReader, ScheduleReader scheduleReader, ConcertReader concertReader, SeatReader seatReader) {
        this.reservationReader = reservationReader;
        this.scheduleReader = scheduleReader;
        this.concertReader = concertReader;
        this.seatReader = seatReader;
    }

    public List<ReservationResponseDto> executor(Long userId){
        List<Reservation> reservations = reservationReader.readReservationsByUserId(userId);

        List<ReservationResponseDto> reservationResponseDtos = new ArrayList<>();

        for(Reservation reservation : reservations){
            Long scheduleId = reservation.getScheduleId();

            /**
             * 해당 예약건 스케쥴 조회
             * */
            Schedule reservedSchedule = scheduleReader.readSchedulesById(scheduleId);
            Long concertId    = reservedSchedule.getConcertId();

            /**
             * 해당 예약건 콘서트 정보 조회
             * */
            Concert reservedConcert = concertReader.readConcertById(concertId);

            /**
             * 해당 예약건 콘서트 정보 조회
             * */
            List<Seat> reservedSeats = reservedSeats = seatReader.readSeatsByScheduleId(scheduleId);

            reservationResponseDtos.add(
                    ReservationResponseDto.
                            builder().
                            concertName(reservedConcert.getConcertName()).
                            concertDesc(reservedConcert.getConertDesc()).
                            reservationId(reservation.getId()).
                            numOfSeats(reservation.getNumOfSeats()).
                            performData(reservedSchedule.getPerformDate()).
                            seats(reservedSeats).
                            build());
        }
        return reservationResponseDtos;
    }
}
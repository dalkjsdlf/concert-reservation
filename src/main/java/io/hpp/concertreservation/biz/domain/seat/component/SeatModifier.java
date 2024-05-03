package io.hpp.concertreservation.biz.domain.seat.component;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatLoadRepository;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatStoreRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SeatModifier{

    private final ISeatStoreRepository seatStoreRepository;
    private final ISeatLoadRepository seatLoadRepository;
    private final SeatValidator seatValidator;

    public void reserveSeats(List<Seat> seats, Long reservationId){

        /*
         * 좌석들 건수가 0건인지 확인
         * */
        seatValidator.validateNoSeat(seats);

        /*
         * 좌석에 예약 ID를 update 하여 좌석별 예약 처리를 한다.
         * */
        seats.forEach(seat->{
            this.modifierReservationIdOfSeat(seat,reservationId);
        });
    }

    public void modifierReservationIdOfSeat(Seat seat, Long reservationId){

        Long seatId = seat.getId();

        /*
         * 이미 있는 좌석에 대해서만 UPDATE 한다.
         * */
        Optional<Seat> optSeat = seatLoadRepository.findSeatBySeatNoAndScheduleId(seat.getSeatNo(), seat.getScheduleId());
        optSeat.orElseThrow(()->new ReservationException(ReservationErrorResult.NO_SEAT));

        /*
         * 좌석에 예약ID 업데이트
         * */
        seatStoreRepository.updateReservationId(seat, reservationId);
    }

}
package io.hpp.concertreservation.biz.domain.seat.component;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatLoadRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class SeatReader{

    private final ISeatLoadRepository seatLoadRepository;
    private final SeatValidator seatValidator;

    public List<Seat> readSeatsByScheduleId(Long scheduleId){
        List<Seat> seats = seatLoadRepository.findSeatsByScheduleId(scheduleId);

        if(seats.isEmpty()){
            throw new ReservationException(ReservationErrorResult.NO_SEATS);
        }

        return seats;
    }

    public Long readScheduleIdOfSeats(List<Seat> seats){
        /**
         * seats 리스트가 0건인지 검사
         * */
        seatValidator.validateNoSeat(seats);

        /**
         * seats가 모두 같은 스케쥴 ID를 가지고 있는지 검사
         * 모두 같은 scheduleId를 가지고 있으면 스케쥴 ID 반환
         * */
        return seatValidator.hasSameScheduleId(seats);
    }

}
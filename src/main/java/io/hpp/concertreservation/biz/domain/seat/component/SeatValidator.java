package io.hpp.concertreservation.biz.domain.seat.component;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class SeatValidator {


    public void validateNoSeat(List<Seat> seats){
        if(seats.isEmpty()){
            throw new ReservationException(ReservationErrorResult.NO_SEATS);
        }
    }

    public Long hasSameScheduleId(List<Seat> seats){
        Long scheduleId = seats.get(0).getScheduleId();

        seats.forEach(seat->{
            if(!seat.getScheduleId().equals(scheduleId)){
                throw new ReservationException(ReservationErrorResult.SEATS_DOES_NOT_HAVE_SAME_SCHEDULE_ID);
            };
        });
        return scheduleId;
    }


}

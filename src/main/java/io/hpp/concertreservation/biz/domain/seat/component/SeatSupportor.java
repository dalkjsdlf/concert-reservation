package io.hpp.concertreservation.biz.domain.seat.component;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class SeatSupportor {

    private final SeatValidator seatValidator;
    private final SeatReader seatReader;

    public Long sumTotalPrice(List<Seat> seats){
        /**
         * seats 리스트가 0건인지 검사
         * */
        seatValidator.validateNoSeat(seats);

        /**
         * 좌석 합계 계산
         * */
        return seats.stream().mapToLong(Seat::getPrice).sum();
    }

    public void checkAlreadyReservedSeatOfSeats(List<Seat> seats){
        for(Seat seat : seats){
            Seat seatForCheck = seatReader.findSeatBySeatNoAndScheduleId(seat.getSeatNo(), seat.getScheduleId());
            if(!seatForCheck.getReserveId().equals(-1L)){
                throw new ReservationException(ReservationErrorResult.ALREADY_SEAT_RESERVED);
            }
        }
    }
}

package io.hpp.concertreservation.biz.domain.seat.component;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class SeatSupportor {

    private final SeatValidator seatValidator;

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
}

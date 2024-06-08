package io.hpp.concertreservation.biz.domain.seat.component;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
@Slf4j
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
        log.info("[{}] 쓰레드 : seatSupportor.checkAlreadReservedSeat 메서드 진입",Thread.currentThread().getName());
        for(Seat seat : seats){
            log.info("[{}] 쓰레드 : 좌석 번호 [{}] read 전",Thread.currentThread().getName(),seat.getSeatNo());
            Seat seatForCheck = seatReader.readSeatBySeatNoAndScheduleId(seat.getSeatNo(), seat.getScheduleId());
            log.info("[{}] 쓰레드 : 좌석 번호 [{}] read 후",Thread.currentThread().getName(),seatForCheck.getSeatNo());
            if(!seatForCheck.getReserveId().equals(-1L)){
                throw new ReservationException(ReservationErrorResult.ALREADY_SEAT_RESERVED);
            }
        }
    }
}

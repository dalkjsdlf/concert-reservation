package io.hpp.concertreservation.biz.domain.seat.component;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatLoadRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class SeatReader{

    private final ISeatLoadRepository seatLoadRepository;
    private final SeatValidator seatValidator;

    public List<Seat> readSeatsByScheduleId(Long scheduleId){

        /*
        * 스케쥴 ID로 좌석 조회
        * **/
        List<Seat> seats = seatLoadRepository.findSeatsByScheduleId(scheduleId);

        /*
         * 예약 되지 않은 좌석만 조회
         * **/
        seats = seats.stream().filter(seat->seat.getReserveId() == -1).collect(Collectors.toList());

        if(seats.isEmpty()){
            throw new ReservationException(ReservationErrorResult.NO_SEATS_AVAILABLE);
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

    public Seat readSeatBySeatId(Long seatId){
        Optional<Seat> optSeat = seatLoadRepository.findSeatBySeatId(seatId);
        return optSeat.orElseThrow(()->new ReservationException(ReservationErrorResult.NO_SEAT));
    }

    public Seat readSeatBySeatNoAndScheduleId(Long seatNo,Long scheduleId){
        Optional<Seat> optSeat = seatLoadRepository.findSeatBySeatNoAndScheduleId(seatNo, scheduleId);
        return optSeat.orElseThrow(()-> new ReservationException(ReservationErrorResult.NO_SEAT));
    }

}
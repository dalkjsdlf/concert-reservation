package io.hpp.concertreservation.biz.api.seat.usecase;

import io.hpp.concertreservation.biz.api.seat.dto.SeatResponseDto;
import io.hpp.concertreservation.biz.domain.seat.component.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
public class GetAllSeatsUseCase{
    private final SeatReader seatReader;

    public GetAllSeatsUseCase(SeatReader seatReader) {
        this.seatReader = seatReader;
    }

    public List<SeatResponseDto> executor(Long scheduleId){
        List<Seat> seats = seatReader.readSeatsByScheduleId(scheduleId);
        return seats.stream().map(seat-> SeatResponseDto.
                builder().
                seatId(seat.getId()).
                seatNo(seat.getSeatNo()).
                build()).collect(Collectors.toList());
    }
}
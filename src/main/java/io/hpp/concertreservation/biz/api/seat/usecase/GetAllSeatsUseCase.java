package io.hpp.concertreservation.biz.api.seat.usecase;

import io.hpp.concertreservation.biz.api.seat.dto.SeatResponseDto;
import io.hpp.concertreservation.biz.domain.seat.component.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllSeatsUseCase{
    private final SeatReader seatReader;

    public List<SeatResponseDto> executor(Long scheduleId){
        List<Seat> seats = seatReader.readSeatsByScheduleId(scheduleId);
        return seats.stream().map(seat-> SeatResponseDto.
                builder().
                seatId(seat.getId()).
                seatNo(seat.getSeatNo()).
                seatGrade(seat.getSeatGrade()).
                price(seat.getPrice()).
                build()).collect(Collectors.toList());
    }
}
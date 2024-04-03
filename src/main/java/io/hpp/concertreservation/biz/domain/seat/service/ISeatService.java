package io.hpp.concertreservation.biz.domain.seat.service;

import io.hpp.concertreservation.biz.api.seat.dto.SeatRequestDto;
import io.hpp.concertreservation.biz.api.seat.dto.SeatResponseDto;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;

import java.util.List;

public interface ISeatService {
    List<SeatResponseDto> getSeatsOnSchedule(Long sechduleId);

    Seat registerSeatOnSchedule(SeatRequestDto seatRequestDto);
}

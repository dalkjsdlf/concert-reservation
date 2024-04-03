package io.hpp.concertreservation.biz.api.seat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SeatResponseDto {

    private Long seatId;
    private Long seatNo;
    private Long scheduleId;
    private Long reserveId;
    private Long price;
}

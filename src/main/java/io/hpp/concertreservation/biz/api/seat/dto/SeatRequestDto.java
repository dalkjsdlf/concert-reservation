package io.hpp.concertreservation.biz.api.seat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SeatRequestDto {

    private final Long scheduleId;
    private final Long seatNo;
    private final Long reserveId;
    private final Long price;
}

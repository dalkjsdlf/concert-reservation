package io.hpp.concertreservation.biz.api.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ReservationRequestDto {
    private final Long scheduleId;
    private final Long reservationid;
}

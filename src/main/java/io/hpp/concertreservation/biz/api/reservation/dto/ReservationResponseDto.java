package io.hpp.concertreservation.biz.api.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ReservationResponseDto {

    private final Long scheduleId;
}

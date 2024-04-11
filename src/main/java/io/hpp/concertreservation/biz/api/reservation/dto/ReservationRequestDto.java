package io.hpp.concertreservation.biz.api.reservation.dto;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class ReservationRequestDto {
    private final Long scheduleId;
    private final Long reservationid;
    private final Long userId;
    private final List<Seat> seats;
}

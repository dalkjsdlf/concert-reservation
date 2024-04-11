package io.hpp.concertreservation.biz.api.reservation.dto;

import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class ReservationResponseDto {
    private final Long reservationId;
    private final Long scheduleId;
    private final String concertName;
    private final String concertDesc;
    private final LocalDateTime performData;
    private final Integer numOfSeats;
    private final List<Seat> seats;
}

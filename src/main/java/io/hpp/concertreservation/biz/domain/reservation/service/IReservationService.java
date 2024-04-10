package io.hpp.concertreservation.biz.domain.reservation.service;

import io.hpp.concertreservation.biz.api.reservation.dto.ReservationResponseDto;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;

import java.util.List;

public interface IReservationService {

    ReservationResponseDto reserveConcert(Long scheduleId, List<Long> seatIds, Long userId);

    ReservationResponseDto cancelReserveConcert(Long reserveId);

    public List<ReservationResponseDto> getReservationsByUserId(Long userId);

    public ReservationResponseDto getReservationsByUserId(Long userId);
}

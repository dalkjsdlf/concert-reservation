package io.hpp.concertreservation.biz.domain.reservation.service;

import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;

import java.util.List;

public interface IReservationService {

    void reserveConcert(Long scheduleId, Long userId);

    void cancelReserveConcert(Long reserveId);
}
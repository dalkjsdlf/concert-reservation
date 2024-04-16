package io.hpp.concertreservation.biz.domain.reservation.repository;


import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationLoadRepository {

    List<Reservation> findReservationsByUserId(Long userId);
    List<Reservation> findAllReservation();

    Optional<Reservation> findReservationByScheduleId(Long scheduleId);
    Optional<Reservation> findReservationById(Long reserveId);




}

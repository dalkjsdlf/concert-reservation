package io.hpp.concertreservation.biz.domain.reservation.repository;


import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationLoadRepository {

    List<Reservation> readReservationsByUserId(Long userId);

    Optional<Reservation> readReservationByScheduleId(Long scheduleId);
    Optional<Reservation> readReservationById(Long reserveId);


}

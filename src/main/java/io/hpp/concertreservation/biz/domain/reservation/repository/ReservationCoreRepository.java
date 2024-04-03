package io.hpp.concertreservation.biz.domain.reservation.repository;

import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationCoreRepository extends JpaRepository<Reservation, Long> {
}

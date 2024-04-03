package io.hpp.concertreservation.biz.domain.reservation.infrastructure;

import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationCoreRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    Optional<Reservation> findByScheduleId(Long scheduleId);
}

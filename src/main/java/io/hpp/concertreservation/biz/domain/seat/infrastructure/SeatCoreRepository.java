package io.hpp.concertreservation.biz.domain.seat.infrastructure;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatCoreRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByScheduleId(Long scheduleId);

    Optional<Seat> findBySeatNoAndScheduleId(Long seatNo, Long scheduleId);
}

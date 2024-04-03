package io.hpp.concertreservation.biz.domain.seat.repository;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatCoreRepository extends JpaRepository<Seat, Long> {
}

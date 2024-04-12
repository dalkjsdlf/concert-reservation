package io.hpp.concertreservation.biz.domain.seat.repository;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface ISeatLoadRepository {
    List<Seat> findSeatsByScheduleId(Long scheduleId);

    Optional<Seat> findSeatBySeatNoAndScheduleId(Long seatNo, Long scheduleId);

    Optional<Seat> findSeatBySeatId(Long seatId);
}

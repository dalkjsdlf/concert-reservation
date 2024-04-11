package io.hpp.concertreservation.biz.domain.userinfo.repository;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;

import java.util.List;
import java.util.Optional;

public interface ISeatLoadRepository {
    List<Seat> readSeatsByScheduleId(Long scheduleId);

    Optional<Seat> readSeatBySeatNoAndScheduleId(Long seatNo, Long scheduleId);

    Optional<Seat> readSeatBySeatId(Long seatId);
}

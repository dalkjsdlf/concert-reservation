package io.hpp.concertreservation.biz.domain.seat.repository;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface ISeatReader {
    List<Seat> readSeatsByScheduleId(Long scheduleId);

    Optional<Seat> readSeatBySeatNoAndScheduleId(Long seatNo, Long scheduleId);

    Optional<Seat> readSeatBySeatId(Long seatId);
}

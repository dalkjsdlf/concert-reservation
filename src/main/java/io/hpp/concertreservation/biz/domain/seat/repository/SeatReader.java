package io.hpp.concertreservation.biz.domain.seat.repository;

import io.hpp.concertreservation.biz.domain.schedule.infrastructure.ScheduleCoreRepository;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.seat.infrastructure.SeatCoreRepository;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SeatReader implements ISeatReader {

    private final SeatCoreRepository seatCoreRepository;

    public SeatReader(@Autowired SeatCoreRepository seatCoreRepository) {
        this.seatCoreRepository = seatCoreRepository;
    }

    @Override
    public List<Seat> readSeatsByScheduleId(Long scheduleId) {
        return seatCoreRepository.findByScheduleId(scheduleId);
    }

    @Override
    public Optional<Seat> readSeatBySeatNoAndScheduleId(Long seatNo, Long scheduleId) {
        return seatCoreRepository.findBySeatNoAndScheduleId(seatNo, scheduleId);
    }

    @Override
    public Optional<Seat> readSeatBySeatId(Long seatId) {
        return seatCoreRepository.findById(seatId);
    }
}

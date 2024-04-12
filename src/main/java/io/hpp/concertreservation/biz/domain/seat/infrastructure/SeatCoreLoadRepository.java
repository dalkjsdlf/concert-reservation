package io.hpp.concertreservation.biz.domain.seat.infrastructure;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatLoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SeatCoreLoadRepository implements ISeatLoadRepository {

    private final ISeatJpaRepository seatJpaRepository;

    public SeatCoreLoadRepository(@Autowired ISeatJpaRepository seatJpaRepository) {
        this.seatJpaRepository = seatJpaRepository;
    }

    @Override
    public List<Seat> findSeatsByScheduleId(Long scheduleId) {
        return seatJpaRepository.findByScheduleId(scheduleId);
    }

    @Override
    public Optional<Seat> findSeatBySeatNoAndScheduleId(Long seatNo, Long scheduleId) {
        return seatJpaRepository.findBySeatNoAndScheduleId(seatNo, scheduleId);
    }

    @Override
    public Optional<Seat> findSeatBySeatId(Long seatId) {
        return seatJpaRepository.findById(seatId);
    }
}

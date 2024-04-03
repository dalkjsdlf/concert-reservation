package io.hpp.concertreservation.biz.domain.seat.repository;

import io.hpp.concertreservation.biz.domain.seat.infrastructure.SeatCoreRepository;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatWriter implements ISeatWriter {

    private final SeatCoreRepository seatCoreRepository;

    public SeatWriter(@Autowired SeatCoreRepository seatCoreRepository) {
        this.seatCoreRepository = seatCoreRepository;
    }

    @Override
    public Seat writeSeat(Seat seat) {
        return seatCoreRepository.save(seat);
    }
}

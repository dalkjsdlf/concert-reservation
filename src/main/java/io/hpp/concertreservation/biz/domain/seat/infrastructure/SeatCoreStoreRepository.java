package io.hpp.concertreservation.biz.domain.seat.infrastructure;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.userinfo.repository.ISeatStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatCoreStoreRepository implements ISeatStoreRepository {

    private final ISeatJpaRepository seatJpaRepository;

    public SeatCoreStoreRepository(@Autowired ISeatJpaRepository seatJpaRepository) {
        this.seatJpaRepository = seatJpaRepository;
    }

    @Override
    public Seat writeSeat(Seat seat) {
        return seatJpaRepository.save(seat);
    }
}

package io.hpp.concertreservation.biz.domain.seat.infrastructure;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class SeatCoreStoreRepository implements ISeatStoreRepository {

    private final ISeatJpaRepository seatJpaRepository;

    @Override
    public Seat saveSeat(Seat seat) {
        return seatJpaRepository.save(seat);
    }
}

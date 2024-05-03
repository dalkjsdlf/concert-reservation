package io.hpp.concertreservation.biz.domain.seat.infrastructure;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class SeatCoreStoreRepository implements ISeatStoreRepository {

    private final ISeatJpaRepository seatJpaRepository;

    @Override
    public Seat updateReservationId(Seat seat, Long reservationId) {
        seat.setReserveId(reservationId);
        return seatJpaRepository.save(seat);
    }

    @Override
    public Seat saveSeat(Seat seat) {
        return seatJpaRepository.save(seat);
    }
}

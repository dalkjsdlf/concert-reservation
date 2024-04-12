package io.hpp.concertreservation.biz.domain.seat.repository;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.stereotype.Component;

public interface ISeatStoreRepository {
    Seat saveSeat(Seat seat);
}

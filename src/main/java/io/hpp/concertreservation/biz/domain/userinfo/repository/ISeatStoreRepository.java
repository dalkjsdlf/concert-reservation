package io.hpp.concertreservation.biz.domain.userinfo.repository;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;

public interface ISeatStoreRepository {
    Seat writeSeat(Seat seat);
}

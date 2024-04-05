package io.hpp.concertreservation.biz.domain.seat.repository;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;

public interface ISeatWriter {
    Seat writeSeat(Seat seat);
}

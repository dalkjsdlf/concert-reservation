package io.hpp.concertreservation.biz.domain.seat.repository;

import io.hpp.concertreservation.biz.domain.concert.model.ConcertInfo;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.stereotype.Component;

public interface ISeatWriter {
    Seat writeSeat(Seat seat);
}

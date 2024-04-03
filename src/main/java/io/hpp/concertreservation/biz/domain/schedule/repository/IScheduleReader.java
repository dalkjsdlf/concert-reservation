package io.hpp.concertreservation.biz.domain.schedule.repository;

import io.hpp.concertreservation.biz.domain.concert.model.ConcertInfo;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IScheduleReader {
    List<Schedule> readConcertsByConcertId(Long concertId);
}

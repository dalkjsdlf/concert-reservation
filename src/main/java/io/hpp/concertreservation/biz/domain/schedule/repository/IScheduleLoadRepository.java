package io.hpp.concertreservation.biz.domain.schedule.repository;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface IScheduleLoadRepository {
    List<Schedule> findConcertsByConcertId(Long concertId);

    Optional<Schedule> findByScheduleId(Long scheduleId);
}

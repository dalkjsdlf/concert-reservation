package io.hpp.concertreservation.biz.domain.schedule.repository;

import io.hpp.concertreservation.biz.domain.concert.model.ConcertInfo;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import org.springframework.stereotype.Component;

@Component
public interface IScheduleWriter {
    ConcertInfo writeSchedule(Schedule schedule);
}

package io.hpp.concertreservation.biz.domain.schedule.repository;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import org.springframework.stereotype.Component;

@Component
public interface IScheduleWriter {
    Schedule writeSchedule(Schedule schedule);
}

package io.hpp.concertreservation.biz.domain.schedule.repository;

import io.hpp.concertreservation.biz.domain.schedule.infrastructure.ScheduleCoreRepository;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleWriter implements IScheduleWriter {

    private final ScheduleCoreRepository scheduleInfoCoreRepository;

    public ScheduleWriter(@Autowired ScheduleCoreRepository scheduleInfoCoreRepository) {
        this.scheduleInfoCoreRepository = scheduleInfoCoreRepository;
    }

    @Override
    public Schedule writeSchedule(Schedule schedule) {
        return scheduleInfoCoreRepository.save(schedule);
    }
}

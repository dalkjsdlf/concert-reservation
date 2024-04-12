package io.hpp.concertreservation.biz.domain.schedule.infrastructure;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleCoreStoreRepository implements IScheduleStoreRepository {

    private final IScheduleJpaRepository scheduleJpaRepository;

    public ScheduleCoreStoreRepository(@Autowired IScheduleJpaRepository scheduleJpaRepository) {
        this.scheduleJpaRepository = scheduleJpaRepository;
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleJpaRepository.save(schedule);
    }
}

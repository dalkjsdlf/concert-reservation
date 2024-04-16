package io.hpp.concertreservation.biz.domain.schedule.infrastructure;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class ScheduleCoreStoreRepository implements IScheduleStoreRepository {

    private final IScheduleJpaRepository scheduleJpaRepository;

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleJpaRepository.save(schedule);
    }
}

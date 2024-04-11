package io.hpp.concertreservation.biz.domain.schedule.infrastructure;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleLoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ScheduleCoreLoadRepository implements IScheduleLoadRepository {

    private final IScheduleJpaRepository scheduleJpaRepository;

    public ScheduleCoreLoadRepository(@Autowired IScheduleJpaRepository scheduleJpaRepository) {
        this.scheduleJpaRepository = scheduleJpaRepository;
    }

    @Override
    public List<Schedule> findConcertsByConcertId(Long concertId) {
        return scheduleJpaRepository.findByConcertId(concertId);
    }

    @Override
    public Optional<Schedule> findByScheduleId(Long scheduleId) {
        return scheduleJpaRepository.findById(scheduleId);
    }


}

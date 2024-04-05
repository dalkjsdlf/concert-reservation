package io.hpp.concertreservation.biz.domain.schedule.repository;

import io.hpp.concertreservation.biz.domain.schedule.infrastructure.ScheduleCoreRepository;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ScheduleReader implements IScheduleReader {

    private final ScheduleCoreRepository scheduleCoreRepository;

    public ScheduleReader(@Autowired ScheduleCoreRepository scheduleCoreRepository) {
        this.scheduleCoreRepository = scheduleCoreRepository;
    }


    @Override
    public List<Schedule> readConcertsByConcertId(Long concertId) {
        return scheduleCoreRepository.findByConcertId(concertId);
    }

    @Override
    public Optional<Schedule> readByScheduleId(Long scheduleId) {
        return scheduleCoreRepository.findById(scheduleId);
    }


}

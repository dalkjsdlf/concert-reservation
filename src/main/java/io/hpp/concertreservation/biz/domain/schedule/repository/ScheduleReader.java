package io.hpp.concertreservation.biz.domain.schedule.repository;

import io.hpp.concertreservation.biz.domain.concert.infrastructure.ConcertInfoCoreRepository;
import io.hpp.concertreservation.biz.domain.concert.model.ConcertInfo;
import io.hpp.concertreservation.biz.domain.schedule.infrastructure.ScheduleCoreRepository;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ScheduleReader implements IScheduleReader {

    private final ScheduleCoreRepository scheduleCoreRepository;

    public ScheduleReader(@Autowired ScheduleCoreRepository scheduleCoreRepository) {
        this.scheduleCoreRepository = scheduleCoreRepository;
    }


    @Override
    public List<Schedule> readConcertsByConcertId(Long concertId) {
        return scheduleCoreRepository.findByConcertId(concertId);
    }

}

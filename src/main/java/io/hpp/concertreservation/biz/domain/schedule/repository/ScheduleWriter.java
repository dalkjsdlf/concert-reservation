package io.hpp.concertreservation.biz.domain.schedule.repository;

import io.hpp.concertreservation.biz.domain.concert.infrastructure.ConcertInfoCoreRepository;
import io.hpp.concertreservation.biz.domain.concert.model.ConcertInfo;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleWriter implements IScheduleWriter {

    private final ConcertInfoCoreRepository concertInfoCoreRepository;

    public ScheduleWriter(@Autowired ConcertInfoCoreRepository concertInfoCoreRepository) {
        this.concertInfoCoreRepository = concertInfoCoreRepository;
    }

    @Override
    public ConcertInfo writeConcert(ConcertInfo concertInfo) {
        return concertInfoCoreRepository.save(concertInfo);
    }
}

package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.infrastructure.ConcertInfoCoreRepository;
import io.hpp.concertreservation.biz.domain.concert.model.ConcertInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConcertInfoWriter implements IConcertInfoWriter{

    private final ConcertInfoCoreRepository concertInfoCoreRepository;

    public ConcertInfoWriter(@Autowired ConcertInfoCoreRepository concertInfoCoreRepository) {
        this.concertInfoCoreRepository = concertInfoCoreRepository;
    }

    @Override
    public ConcertInfo writeConcert(ConcertInfo concertInfo) {
        return concertInfoCoreRepository.save(concertInfo);
    }
}

package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.infrastructure.ConcertInfoCoreRepository;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.beans.factory.annotation.Autowired;

public class ConcertInfoWriter implements IConcertInfoWriter{

    private final ConcertInfoCoreRepository concertInfoCoreRepository;

    public ConcertInfoWriter(@Autowired ConcertInfoCoreRepository concertInfoCoreRepository) {
        this.concertInfoCoreRepository = concertInfoCoreRepository;
    }

    @Override
    public Concert writeConcert(Concert concert) {
        return concertInfoCoreRepository.save(concert);
    }
}

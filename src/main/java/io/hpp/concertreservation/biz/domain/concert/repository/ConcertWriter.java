package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.infrastructure.ConcertCoreRepository;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConcertWriter implements IConcertWriter {

    private final ConcertCoreRepository concertCoreRepository;

    public ConcertWriter(@Autowired ConcertCoreRepository concertCoreRepository) {
        this.concertCoreRepository = concertCoreRepository;
    }

    @Override
    public Concert writeConcert(Concert concert) {
        return concertCoreRepository.save(concert);
    }
}

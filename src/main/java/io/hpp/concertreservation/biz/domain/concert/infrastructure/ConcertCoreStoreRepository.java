package io.hpp.concertreservation.biz.domain.concert.infrastructure;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConcertCoreStoreRepository implements IConcertStoreRepository {

    private final IConcertJpaRepository concertJpaRepository;
    public ConcertCoreStoreRepository(@Autowired IConcertJpaRepository concertJpaRepository) {
        this.concertJpaRepository = concertJpaRepository;
    }

    @Override
    public Concert saveConcert(Concert concert) {
        return concertJpaRepository.save(concert);
    }
}

package io.hpp.concertreservation.biz.domain.concert.infrastructure;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class ConcertCoreStoreRepository implements IConcertStoreRepository {

    private final IConcertJpaRepository concertJpaRepository;

    @Override
    public Concert saveConcert(Concert concert) {
        return concertJpaRepository.save(concert);
    }
}

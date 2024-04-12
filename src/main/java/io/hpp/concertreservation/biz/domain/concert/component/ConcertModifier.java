package io.hpp.concertreservation.biz.domain.concert.component;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertStoreRepository;
import org.springframework.stereotype.Component;

@Component
public class ConcertModifier {

    private final IConcertStoreRepository concertStoreRepository;

    public ConcertModifier(IConcertStoreRepository concertStoreRepository) {
        this.concertStoreRepository = concertStoreRepository;
    }

    public void addSave(Concert concert){
        concertStoreRepository.saveConcert(concert);
    }
}
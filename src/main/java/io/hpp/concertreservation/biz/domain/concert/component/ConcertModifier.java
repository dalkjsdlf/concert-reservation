package io.hpp.concertreservation.biz.domain.concert.component;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class ConcertModifier {

    private final IConcertStoreRepository concertStoreRepository;

    public void addSave(Concert concert){
        concertStoreRepository.saveConcert(concert);
    }
}
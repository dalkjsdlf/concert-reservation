package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.infrastructure.ConcertCoreRepository;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConcertReader implements IConcertReader {

    private final ConcertCoreRepository concertCoreRepository;

    public ConcertReader(@Autowired ConcertCoreRepository concertCoreRepository) {
        this.concertCoreRepository = concertCoreRepository;
    }

    @Override
    public List<Concert> readAllConcerts() {
        return concertCoreRepository.findAll();
    }

    @Override
    public Optional<Concert> readConcert(Long concertId) {
        return concertCoreRepository.findById(concertId);
    }
}

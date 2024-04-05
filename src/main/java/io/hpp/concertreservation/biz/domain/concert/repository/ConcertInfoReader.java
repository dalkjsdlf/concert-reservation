package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.infrastructure.ConcertInfoCoreRepository;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ConcertInfoReader implements IConcertInfoReader{

    private final ConcertInfoCoreRepository concertInfoCoreRepository;

    public ConcertInfoReader(@Autowired ConcertInfoCoreRepository concertInfoCoreRepository) {
        this.concertInfoCoreRepository = concertInfoCoreRepository;
    }

    @Override
    public List<Concert> readAllConcerts() {
        return concertInfoCoreRepository.findAll();
    }

    @Override
    public Optional<Concert> readConcert(Long concertId) {
        return concertInfoCoreRepository.findById(concertId);
    }
}

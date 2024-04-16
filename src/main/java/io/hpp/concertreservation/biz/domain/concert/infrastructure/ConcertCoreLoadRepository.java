package io.hpp.concertreservation.biz.domain.concert.infrastructure;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertLoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ConcertCoreLoadRepository implements IConcertLoadRepository {

    private final IConcertJpaRepository IConcertJpaRepository;

    @Override
    public List<Concert> findAllConcerts() {
        return IConcertJpaRepository.findAll();
    }

    @Override
    public Optional<Concert> findConcert(Long concertId) {
        return IConcertJpaRepository.findById(concertId);
    }
}

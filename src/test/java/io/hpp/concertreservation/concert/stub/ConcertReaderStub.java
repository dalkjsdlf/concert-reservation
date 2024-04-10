package io.hpp.concertreservation.concert.stub;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertReader;

import java.util.List;
import java.util.Optional;

public class ConcertReaderStub implements IConcertReader {

    @Override
    public List<Concert> readAllConcerts() {



        return null;
    }

    @Override
    public Optional<Concert> readConcert(Long concertId) {
        return Optional.empty();
    }
}

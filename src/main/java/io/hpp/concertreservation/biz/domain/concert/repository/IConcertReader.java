package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface IConcertReader {
    List<Concert> readAllConcerts();
    Optional<Concert> readConcert(Long concertId);
}

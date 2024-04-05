package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IConcertInfoReader {
    List<Concert> readAllConcerts();
    Optional<Concert> readConcert(Long concertId);
}

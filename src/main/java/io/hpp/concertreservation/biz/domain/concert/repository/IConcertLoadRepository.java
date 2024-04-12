package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;

import java.util.List;
import java.util.Optional;

public interface IConcertLoadRepository {
    List<Concert> findAllConcerts();
    Optional<Concert> findConcert(Long concertId);
}

package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;

public interface IConcertStoreRepository {
    Concert writeConcert(Concert concert);
}

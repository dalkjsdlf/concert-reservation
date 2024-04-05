package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.stereotype.Component;

@Component
public interface IConcertInfoWriter {
    Concert writeConcert(Concert concert);
}

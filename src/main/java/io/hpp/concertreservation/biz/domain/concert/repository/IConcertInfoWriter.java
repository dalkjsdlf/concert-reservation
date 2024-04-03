package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.model.ConcertInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IConcertInfoWriter {
    ConcertInfo writeConcert(ConcertInfo concertInfo);
}

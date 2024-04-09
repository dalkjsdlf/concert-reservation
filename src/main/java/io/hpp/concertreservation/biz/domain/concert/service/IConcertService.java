package io.hpp.concertreservation.biz.domain.concert.service;

import io.hpp.concertreservation.biz.api.concert.dto.ConcertResponseDto;

import java.util.List;

public interface IConcertService {

    List<ConcertResponseDto> getAllConcerts();
    ConcertResponseDto getConcertById(Long concertId);
}

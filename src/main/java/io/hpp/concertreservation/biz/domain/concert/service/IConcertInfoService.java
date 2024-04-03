package io.hpp.concertreservation.biz.domain.concert.service;

import io.hpp.concertreservation.biz.api.concert.dto.ConcertInfoResponseDto;

import java.util.List;

public interface IConcertInfoService {

    List<ConcertInfoResponseDto> getAllConcerts();
    ConcertInfoResponseDto getConcertById(Long concertId);
}

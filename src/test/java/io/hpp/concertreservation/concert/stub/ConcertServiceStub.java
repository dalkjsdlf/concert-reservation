package io.hpp.concertreservation.concert.stub;

import io.hpp.concertreservation.biz.api.concert.dto.ConcertResponseDto;
import io.hpp.concertreservation.biz.domain.concert.service.IConcertService;

import java.util.List;

public class ConcertServiceStub implements IConcertService {

    @Override
    public List<ConcertResponseDto> getAllConcerts() {
        return List.of(ConcertResponseDto.builder().build(),ConcertResponseDto.builder().build());
    }

    @Override
    public ConcertResponseDto getConcertById(Long concertId) {
        return ConcertResponseDto.builder().build();
    }
}

package io.hpp.concertreservation.biz.api.concert.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class ConcertInfoResponseDto {

    private final Long id;
    private final String concertName;
    private final String conertDesc;
    private final String artist;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

}

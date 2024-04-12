package io.hpp.concertreservation.biz.api.concert.usecase;

import io.hpp.concertreservation.biz.api.concert.dto.ConcertResponseDto;
import io.hpp.concertreservation.biz.domain.concert.component.ConcertReader;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
@Slf4j
public class GetAllConcertsUseCase {

    private final ConcertReader concertReader;

    public GetAllConcertsUseCase(@Autowired ConcertReader concertReader) {
        this.concertReader = concertReader;
    }

    public List<ConcertResponseDto> executor(){
        List<Concert> concerts = new ArrayList<>();
        try{
            concerts = concertReader.readAllConcerts();
        }catch (ReservationException e){
            if(e.getErrorResult() == ReservationErrorResult.NOT_FOUND_CONCERT){
                log.info("콘서트조회가 0건입니다.");
            }
        }
        return concerts.stream().map(concert->ConcertResponseDto.
                builder().
                id(concert.getId()).
                artist(concert.getArtist()).
                concertName(concert.getConcertName()).
                conertDesc(concert.getConertDesc()).
                startDate(concert.getStartDate()).
                endDate(concert.getEndDate()).
                build()).collect(Collectors.toList());
    }
}
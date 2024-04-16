package io.hpp.concertreservation.biz.api.concert.usecase;

import io.hpp.concertreservation.biz.api.concert.dto.ConcertResponseDto;
import io.hpp.concertreservation.biz.domain.concert.component.ConcertReader;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetConcertUseCase{

    private final ConcertReader concertReader;


    public ConcertResponseDto executor(Long concertId){
        Concert concert = concertReader.readConcertById(concertId);

        return ConcertResponseDto.
                builder().
                id(concert.getId()).
                artist(concert.getArtist()).
                concertName(concert.getConcertName()).
                conertDesc(concert.getConertDesc()).
                startDate(concert.getStartDate()).
                endDate(concert.getEndDate()).
                build();
    }
}
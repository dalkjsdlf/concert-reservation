package io.hpp.concertreservation.biz.domain.concert.service;

import io.hpp.concertreservation.biz.api.concert.dto.ConcertResponseDto;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.ConcertReader;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class ConcertService implements IConcertService {

    private final ConcertReader concertReader;

    /**
     * Instantiates a new Concert info service.
     *
     * @param concertReader the concert info reader
     */
    public ConcertService(@Autowired ConcertReader concertReader) {
        this.concertReader = concertReader;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ConcertResponseDto> getAllConcerts() {

        List<Concert> concerts = concertReader.readAllConcerts();

        if(concerts.isEmpty()){
            throw new ReservationException(ReservationErrorResult.ANY_CONCERT_NOT_FOUND);
        }

        return convResponseListDto(concerts);
    }

    /**
     *
     * @param concertId
     * @return
     */
    @Override
    public ConcertResponseDto getConcertById(Long concertId) {
        Optional<Concert> optConcertInfo = concertReader.readConcert(concertId);
        Concert foundConcert = optConcertInfo.orElseThrow(() -> new ReservationException(ReservationErrorResult.NOT_FOUND_CONCERT));

        return convResponseDto(foundConcert);
    }

    private ConcertResponseDto convResponseDto(Concert concert){
        return ConcertResponseDto
                .builder()
                .id(concert.getId())
                .concertName(concert.getConcertName())
                .artist(concert.getArtist())
                .conertDesc(concert.getConertDesc())
                .startDate(concert.getStartDate())
                .endDate(concert.getEndDate())
                .build();
    }

    private List<ConcertResponseDto> convResponseListDto(List<Concert> concertList){
        return concertList
                .stream()
                .map(concertInfo ->
                    ConcertResponseDto
                            .builder()
                            .id(concertInfo.getId())
                            .concertName(concertInfo.getConcertName())
                            .artist(concertInfo.getArtist())
                            .conertDesc(concertInfo.getConertDesc())
                            .startDate(concertInfo.getStartDate())
                            .endDate(concertInfo.getEndDate())
                            .build()).collect(Collectors.toList());
    }


}

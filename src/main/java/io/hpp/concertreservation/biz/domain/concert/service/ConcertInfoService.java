package io.hpp.concertreservation.biz.domain.concert.service;

import io.hpp.concertreservation.biz.api.concert.dto.ConcertInfoResponseDto;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.ConcertInfoReader;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class ConcertInfoService implements IConcertInfoService {

    private final ConcertInfoReader concertInfoReader;

    /**
     * Instantiates a new Concert info service.
     *
     * @param concertInfoReader the concert info reader
     */
    public ConcertInfoService(@Autowired ConcertInfoReader concertInfoReader) {
        this.concertInfoReader = concertInfoReader;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ConcertInfoResponseDto> getAllConcerts() {

        List<Concert> concerts = concertInfoReader.readAllConcerts();

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
    public ConcertInfoResponseDto getConcertById(Long concertId) {
        Optional<Concert> optConcertInfo = concertInfoReader.readConcert(concertId);
        Concert foundConcert = optConcertInfo.orElseThrow(() -> new ReservationException(ReservationErrorResult.NOT_FOUND_CONCERT));

        return convResponseDto(foundConcert);
    }

    private ConcertInfoResponseDto convResponseDto(Concert concert){
        return ConcertInfoResponseDto
                .builder()
                .id(concert.getId())
                .concertName(concert.getConcertName())
                .artist(concert.getArtist())
                .conertDesc(concert.getConertDesc())
                .startDate(concert.getStartDate())
                .endDate(concert.getEndDate())
                .build();
    }

    private List<ConcertInfoResponseDto> convResponseListDto(List<Concert> concertList){
        return concertList
                .stream()
                .map(concertInfo ->
                    ConcertInfoResponseDto
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

package io.hpp.concertreservation.biz.domain.concert.component;

import io.hpp.concertreservation.biz.api.concert.dto.ConcertResponseDto;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertLoadRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConcertReader{

    private final IConcertLoadRepository concertLoadRepository;

    public ConcertReader(@Autowired IConcertLoadRepository concertLoadRepository) {
        this.concertLoadRepository = concertLoadRepository;
    }

    public List<Concert> readAllConcerts(){
        List<Concert> concerts = concertLoadRepository.readAllConcerts();

        if(concerts.isEmpty()){
            throw new ReservationException(ReservationErrorResult.ANY_CONCERT_NOT_FOUND);
        }

        return concerts;
    }
    public Concert readConcertById(Long concertId){

        Optional<Concert> optConcertInfo = concertLoadRepository.readConcert(concertId);
        /**
         * 콘서트 조회시 찾지 못하면 발생
         * */
        return optConcertInfo.orElseThrow(() -> new ReservationException(ReservationErrorResult.NOT_FOUND_CONCERT));
    }
}
package io.hpp.concertreservation.biz.api.concert.controller;

import io.hpp.concertreservation.biz.api.concert.dto.ConcertResponseDto;
import io.hpp.concertreservation.biz.api.concert.usecase.GetAllConcertsUseCase;
import io.hpp.concertreservation.biz.api.concert.usecase.GetConcertUseCase;
import io.hpp.concertreservation.common.annotation.ValidationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/concerts")
@RequiredArgsConstructor
@Slf4j
public class ConcertController {

    private final GetAllConcertsUseCase getAllConcertsUseCase;

    private final GetConcertUseCase getConcertUseCase;

    /*
     * /api/concerts
     * */
    @GetMapping
    @ValidationToken
    public ResponseEntity<List<ConcertResponseDto>> getAllConcerts(
    ){
        log.info("concert controller 접근");
        return ResponseEntity.ok(getAllConcertsUseCase.executor());
    }

    /*
     * /api/concerts/{concertId}
     * */
    @GetMapping("{concertId}")
    @ValidationToken
    public ResponseEntity<ConcertResponseDto> getConcert(
            @PathVariable("concertId") Long concertId){
        log.info("concert controller ID로 조회 접근");
        return ResponseEntity.ok(getConcertUseCase.executor(concertId));
    }
}

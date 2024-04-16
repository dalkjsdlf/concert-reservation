package io.hpp.concertreservation.biz.api.concert.controller;

import io.hpp.concertreservation.biz.api.concert.dto.ConcertResponseDto;
import io.hpp.concertreservation.biz.api.concert.usecase.GetAllConcertsUseCase;
import io.hpp.concertreservation.biz.api.concert.usecase.GetConcertUseCase;
import io.hpp.concertreservation.common.annotation.ValidationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

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
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){
        log.info("concert controller 접근");
        return ResponseEntity.ok(getAllConcertsUseCase.executor());
        //return ResponseEntity.ok(new ArrayList<ConcertResponseDto>());
    }

    /*
     * /api/concerts/{concertId}
     * */
    @GetMapping("{concertId}")
    public ResponseEntity<ConcertResponseDto> getConcert(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @PathVariable("concertId") Long concertId){
        log.info("concert controller ID로 조회 접근");
        return ResponseEntity.ok(getConcertUseCase.executor(concertId));
    }
}

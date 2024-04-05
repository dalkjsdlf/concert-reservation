package io.hpp.concertreservation.biz.api.concert.controller;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RestController
@RequestMapping("/api/concerts")
public class ConcertController {

    /*
    * /api/concerts
    * */
    @GetMapping("")
    public ResponseEntity<List<Concert>> getAllConcerts(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){
        return ResponseEntity.ok(new ArrayList<Concert>());
    }

    /*
     * /api/concerts/{concertId}
     * */
    @GetMapping("{concertId}")
    public ResponseEntity<Concert> getAllConcerts(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @PathVariable("concertId") Long concertId){
        return ResponseEntity.ok(Concert.of("name","desc","artist", LocalDateTime.now(),LocalDateTime.now()));
    }
}

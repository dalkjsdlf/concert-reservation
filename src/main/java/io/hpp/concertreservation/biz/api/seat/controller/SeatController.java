package io.hpp.concertreservation.biz.api.seat.controller;

import io.hpp.concertreservation.biz.api.seat.dto.SeatResponseDto;
import io.hpp.concertreservation.biz.api.seat.usecase.GetAllSeatsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RestController
@RequestMapping("/api/concerts/{concertId}/schedules/{scheduleId}/seats")
@RequiredArgsConstructor
@Slf4j
public class SeatController {

    private final GetAllSeatsUseCase getAllSeatsUseCase;

    /*
     * /api/concerts/{concertId}/schedules/
     * */
    @GetMapping("")
    public ResponseEntity<List<SeatResponseDto>> getAllSeats(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("concertId") Long concertId){
        log.info("scheduleId [{}]",scheduleId);
        return ResponseEntity.ok(getAllSeatsUseCase.executor(scheduleId));
    }

}

package io.hpp.concertreservation.biz.api.seat.controller;

import io.hpp.concertreservation.biz.api.seat.dto.SeatResponseDto;
import io.hpp.concertreservation.biz.api.seat.usecase.GetAllSeatsUseCase;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RequestMapping("/api/concert/{concertId}/schdeule/{scheduleId}/seats")
@RestController
public class SeatController {

    private final GetAllSeatsUseCase getAllSeatsUseCase;

    public SeatController(@Autowired GetAllSeatsUseCase getAllSeatsUseCase) {
        this.getAllSeatsUseCase = getAllSeatsUseCase;
    }

    /*
     * /api/concerts/{concertId}/schedules/
     * */
    @GetMapping("")
    public ResponseEntity<List<SeatResponseDto>> getAllSeats(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @PathVariable("scheduleId") Long scheduleId
    ){
        return ResponseEntity.ok(getAllSeatsUseCase.executor(scheduleId));
    }

}

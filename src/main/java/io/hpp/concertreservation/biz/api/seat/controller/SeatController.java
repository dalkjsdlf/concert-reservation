package io.hpp.concertreservation.biz.api.seat.controller;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RequestMapping("/api/concert/{concertId}/schdeule/{scheduleId}")
@RestController
public class SeatController {


    /*
     * /api/concerts/{concertId}/schedules/
     * */
    @GetMapping("seats")
    public ResponseEntity<List<Seat>> getAllSeats(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){

        return ResponseEntity.ok(new ArrayList<Seat>());
    }

}

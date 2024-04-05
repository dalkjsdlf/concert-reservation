package io.hpp.concertreservation.biz.api.schedule.controller;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RestController
@RequestMapping("/api/concerts")
public class ScheduleController {

    /*
     * /api/concerts/{concertId}/schedules/
     * */
    @GetMapping("{concertId}/schedules")
    public ResponseEntity<List<Schedule>> getAllSchedules(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){
        return ResponseEntity.ok(new ArrayList<Schedule>());
    }

    /*
     * /api/concerts/{concertId}/schedules/{ScheduleId}
     * */
    @GetMapping("{concertId}/schedules/{scheduleId}")
    public ResponseEntity<Schedule> getAllSchedules(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @PathVariable("concertId") Long concertId,
            @PathVariable("scheduleId") Long scheduleId){

        return ResponseEntity.ok(Schedule.of(concertId,LocalDateTime.now()));
    }
}

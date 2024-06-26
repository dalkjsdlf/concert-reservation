package io.hpp.concertreservation.biz.api.schedule.controller;

import io.hpp.concertreservation.biz.api.schedule.dto.ScheduleResponseDto;
import io.hpp.concertreservation.biz.api.schedule.usecase.GetAllSchedulesUseCase;
import io.hpp.concertreservation.biz.api.schedule.usecase.GetScheduleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RestController
@RequestMapping("/api/concerts/{concertId}/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final GetAllSchedulesUseCase getAllSchedulesUseCase;
    private final GetScheduleUseCase getScheduleUseCase;

    /*
     * /api/concerts/{concertId}/schedules/
     * */
    @GetMapping("")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @PathVariable("concertId") Long concertId
    ){

        return ResponseEntity.ok(getAllSchedulesUseCase.executor(concertId));
    }

    /*
     * /api/concerts/{concertId}/schedules/{ScheduleId}
     * */
    @GetMapping("{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @PathVariable("concertId") Long concertId,
            @PathVariable("scheduleId") Long scheduleId){

        return ResponseEntity.ok(getScheduleUseCase.execute(scheduleId));
    }
}

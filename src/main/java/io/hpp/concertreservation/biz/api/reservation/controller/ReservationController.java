package io.hpp.concertreservation.biz.api.reservation.controller;

import io.hpp.concertreservation.biz.api.reservation.dto.ReservationRequestDto;
import io.hpp.concertreservation.biz.api.reservation.dto.ReservationResponseDto;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    /*
     * /api/reservations/status
     * */
    @GetMapping("status")
    public ResponseEntity<List<ReservationResponseDto>> getReservationStatusOfUser(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){
        return ResponseEntity.ok(new ArrayList<ReservationResponseDto>());
    }

    /*
     * /api/reservations/
     * */
    @GetMapping("")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsOfUser(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){
        return ResponseEntity.ok(new ArrayList<ReservationResponseDto>());
    }

    /*
     * /api/reservations
     * */
    @PostMapping("")
    public ResponseEntity<ReservationResponseDto> reserveConcert(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @RequestBody final ReservationRequestDto reservationRequestDto
    ){

        return ResponseEntity.status(HttpStatus.CREATED).body(new ReservationResponseDto(reservationRequestDto.getScheduleId()));
    }

    /*
     * /api/reservations/cancel
     * */
    @PostMapping("cancel")
    public ResponseEntity<ReservationResponseDto> cancelConcert(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @RequestBody final ReservationRequestDto reservationRequestDto
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ReservationResponseDto(reservationRequestDto.getScheduleId()));
    }
}

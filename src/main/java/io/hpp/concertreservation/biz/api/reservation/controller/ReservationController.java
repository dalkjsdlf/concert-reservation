package io.hpp.concertreservation.biz.api.reservation.controller;

import io.hpp.concertreservation.biz.api.reservation.dto.ReservationRequestDto;
import io.hpp.concertreservation.biz.api.reservation.dto.ReservationResponseDto;
import io.hpp.concertreservation.biz.api.reservation.usecase.GetAllReservationsUseCase;
import io.hpp.concertreservation.biz.api.reservation.usecase.ReserveConcertUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReserveConcertUseCase reserveConcertUseCase;
    private final GetAllReservationsUseCase getAllReservationsUseCase;

    public ReservationController(ReserveConcertUseCase reserveConcertUseCase, GetAllReservationsUseCase getAllReservationsUseCase) {
        this.reserveConcertUseCase = reserveConcertUseCase;
        this.getAllReservationsUseCase = getAllReservationsUseCase;
    }

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
        return ResponseEntity.ok(getAllReservationsUseCase.executor(userId));
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
        reserveConcertUseCase.execute(reservationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
     * /api/reservations/cancel dummy
     * */
    @PostMapping("cancel")
    public ResponseEntity<ReservationResponseDto> cancelConcert(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @RequestBody final ReservationRequestDto reservationRequestDto
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ReservationResponseDto.
                        builder().
                        scheduleId(reservationRequestDto.getScheduleId()).
                        build()
        );
    }
}

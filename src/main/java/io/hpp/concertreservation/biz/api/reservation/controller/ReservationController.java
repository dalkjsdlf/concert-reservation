package io.hpp.concertreservation.biz.api.reservation.controller;

import io.hpp.concertreservation.biz.api.reservation.dto.ReservationRequestDto;
import io.hpp.concertreservation.biz.api.reservation.dto.ReservationResponseDto;
import io.hpp.concertreservation.biz.api.reservation.usecase.GetAllReservationsUseCase;
import io.hpp.concertreservation.biz.api.reservation.usecase.ReserveConcertUseCase;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReserveConcertUseCase reserveConcertUseCase;
    private final GetAllReservationsUseCase getAllReservationsUseCase;

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
        List<Seat> seats = reservationRequestDto.getSeats();
        log.info("seat 확인 [{}]건 ", seats.size());

        seats.forEach(seat->log.info("seat 개별 [{}]",seat));

        reserveConcertUseCase.execute(seats, userId);
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

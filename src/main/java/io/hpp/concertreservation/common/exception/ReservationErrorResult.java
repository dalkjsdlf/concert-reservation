package io.hpp.concertreservation.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReservationErrorResult {
    NOT_FOUND_CONCERT(HttpStatus.BAD_REQUEST,"The lecture is not found"),
    WRONG_USER_ID(HttpStatus.BAD_REQUEST,"The ID was entered incorrectly."),
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Exception"),
    ANY_CONCERT_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR,"Any Concert Not Found"),
    NO_SCHEDULE(HttpStatus.INTERNAL_SERVER_ERROR,"There are no schedules of the concert"),
    NO_SEATS(HttpStatus.INTERNAL_SERVER_ERROR,"There are no seats of the schedule"),
    ALREADY_SEAT_REGISTERED(HttpStatus.BAD_REQUEST,"The seat with seat no on the schedule already exists"),
    NEGATIVE_PRICE(HttpStatus.BAD_REQUEST,"price value is negative");

    private final HttpStatus httpStatus;
    private final String message;
}

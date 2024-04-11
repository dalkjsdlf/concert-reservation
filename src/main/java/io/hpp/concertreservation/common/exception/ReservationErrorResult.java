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
    NO_SEAT_INPUT(HttpStatus.INTERNAL_SERVER_ERROR,"you should enter seats"),
    NO_SEAT(HttpStatus.INTERNAL_SERVER_ERROR,"There is no seat of the seat"),
    ALREADY_SEAT_REGISTERED(HttpStatus.BAD_REQUEST,"The seat with seat no on the schedule already exists"),
    NEGATIVE_PRICE(HttpStatus.BAD_REQUEST,"price value is negative"),
    NO_SEAT_SELECTED(HttpStatus.BAD_REQUEST,"Any seats not selected"),
    ALREADY_SEAT_RESERVED(HttpStatus.INTERNAL_SERVER_ERROR,"The seat is already reserved"),
    NO_RESERVATION(HttpStatus.INTERNAL_SERVER_ERROR,"No Reservation"),
    NOT_ENOUGH_MONEY(HttpStatus.BAD_REQUEST,"Not Enough Money"),
    WRONG_SEAT_INPUT(HttpStatus.BAD_REQUEST,"Wrong seat input"),
    FAIL_DELETE_NO_RESERVATION(HttpStatus.BAD_REQUEST,"Failed to delete caused by that cannot found reservation"),
    NO_PAYMENT_HISTORY(HttpStatus.INTERNAL_SERVER_ERROR,"There is no payment histories"),
    SEATS_DOES_NOT_HAVE_SAME_SCHEDULE_ID(HttpStatus.INTERNAL_SERVER_ERROR, "seats doesn't have the same scheduleId");

    private final HttpStatus httpStatus;
    private final String message;
}

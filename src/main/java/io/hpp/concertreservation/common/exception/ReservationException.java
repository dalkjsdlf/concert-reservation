package io.hpp.concertreservation.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReservationException extends RuntimeException{

    private final ReservationErrorResult errorResult;
}

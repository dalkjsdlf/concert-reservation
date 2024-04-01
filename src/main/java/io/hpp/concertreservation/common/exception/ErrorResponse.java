package io.hpp.concertreservation.common.exception;

public record ErrorResponse(
        String code,
        String message
) {
}

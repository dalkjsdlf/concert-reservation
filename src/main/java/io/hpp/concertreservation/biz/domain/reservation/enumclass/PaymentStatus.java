package io.hpp.concertreservation.biz.domain.reservation.enumclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatus {
    WAIT("WAIT"),
    COMPLETE("COMP"),
    ;

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PaymentStatus from(String value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

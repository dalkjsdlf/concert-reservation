package io.hpp.concertreservation.biz.domain.waitqueue.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WaitStatus {
    WORK("WORK"),
    WAIT("WAIT"),
    DONE("DONE"),

    ;

    private final String value;

    WaitStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static WaitStatus from(String value) {
        for (WaitStatus status : WaitStatus.values()) {
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

package io.hpp.concertreservation.biz.domain.seat.enumclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.hpp.concertreservation.biz.domain.userpayment.enumclass.PayMethod;

public enum SeatGrade {
    VIP("VIP"),
    R("R"),
    S("S"),
    A("A"),

    ;

    private final String value;

    SeatGrade(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SeatGrade from(String value) {
        for (SeatGrade status : SeatGrade.values()) {
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

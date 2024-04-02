package io.hpp.concertreservation.biz.domain.payment.enumclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PayType {
    CHARGE("charge"),
    USE("use"),;

    private final String value;

    PayType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PayType from(String value) {
        for (PayType status : PayType.values()) {
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

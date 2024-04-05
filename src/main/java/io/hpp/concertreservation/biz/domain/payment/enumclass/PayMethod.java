package io.hpp.concertreservation.biz.domain.payment.enumclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PayMethod {
    CARD("card"),
    POINT("point"),

    PAY_MONEY("pay_money"),;

    private final String value;

    PayMethod(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PayMethod from(String value) {
        for (PayMethod status : PayMethod.values()) {
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

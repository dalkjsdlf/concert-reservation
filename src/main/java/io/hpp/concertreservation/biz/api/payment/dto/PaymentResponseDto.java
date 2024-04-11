package io.hpp.concertreservation.biz.api.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class PaymentResponseDto {
    private final Long reservationid;
    private final String concertName;
    private final LocalDateTime tranDate;
    private final Long amount;
}

package io.hpp.concertreservation.biz.api.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class PaymentResponseDto {

    private final Long reservationid;
}

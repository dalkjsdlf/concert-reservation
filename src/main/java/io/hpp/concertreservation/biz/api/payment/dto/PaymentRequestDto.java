package io.hpp.concertreservation.biz.api.payment.dto;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class PaymentRequestDto {
    private final Long reservationId;
    private final PayMethod payMethod;
}

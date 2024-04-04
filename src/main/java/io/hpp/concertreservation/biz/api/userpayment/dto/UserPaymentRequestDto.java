package io.hpp.concertreservation.biz.api.userpayment.dto;


import io.hpp.concertreservation.biz.domain.userpayment.enumclass.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class UserPaymentRequestDto {
    Long userId;
    PayMethod payMethod;
    Long price;
    Long reserveId;
}

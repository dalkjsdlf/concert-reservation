package io.hpp.concertreservation.biz.api.paymoney.dto;


import io.hpp.concertreservation.biz.domain.paymoney.enumclass.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class PayMoneyRequestDto {
    Long userId;
    PayMethod payMethod;
    Long price;
    Long reserveId;
}

package io.hpp.concertreservation.biz.api.paymoney.usecase;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyResponseDto;
import io.hpp.concertreservation.biz.domain.paymoney.component.PayMoneyReader;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPayMoneyUseCase {

    private final PayMoneyReader payMoneyReader;

    public GetPayMoneyUseCase(@Autowired PayMoneyReader payMoneyReader) {
        this.payMoneyReader = payMoneyReader;
    }

    public PayMoneyResponseDto execute(Long userId, PayMethod payMethod){
        PayMoney payMoney = payMoneyReader.readPaymoney(userId,payMethod);

        PayMoneyResponseDto.builder().userId(payMoney.getUserId()).
    }
}

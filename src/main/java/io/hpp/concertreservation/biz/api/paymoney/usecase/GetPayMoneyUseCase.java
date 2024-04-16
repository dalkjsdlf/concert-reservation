package io.hpp.concertreservation.biz.api.paymoney.usecase;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyResponseDto;
import io.hpp.concertreservation.biz.domain.paymoney.component.PayMoneyReader;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetPayMoneyUseCase {

    private final PayMoneyReader payMoneyReader;

    public PayMoneyResponseDto execute(Long userId, PayMethod payMethod){
        PayMoney payMoney = payMoneyReader.readPaymoney(userId,payMethod);

        return PayMoneyResponseDto.
                builder().
                userId(payMoney.getUserId()).
                payMethod(payMoney.getPayMethod()).
                balance(payMoney.getBalance()).
                build();
    }
}

package io.hpp.concertreservation.biz.domain.paymoney.component;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyLoadRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@RequiredArgsConstructor
@Component
public class PayMoneyValidator
{
    private final IPayMoneyLoadRepository payMoneyLoadRepository;

    public PayMoney validateEnoughMoney(Long userId, PayMethod payMethod, Long price){

        Optional<PayMoney> optPayMoney = payMoneyLoadRepository.findByUserIdAndPayMethod(userId, payMethod);
        PayMoney payMoney = optPayMoney.orElseThrow(()->new ReservationException(ReservationErrorResult.NOT_ENOUGH_MONEY));

        Long balance = payMoney.getBalance();

        if(balance.compareTo(price) < 0){
            throw new ReservationException(ReservationErrorResult.NOT_ENOUGH_MONEY);
        }

        return payMoney;
    }

    public PayMoney validateExistsPayMoney(Long userId, PayMethod payMethod){
        Optional<PayMoney> optPayMoney = payMoneyLoadRepository.findByUserIdAndPayMethod(userId, payMethod);
        return optPayMoney.orElseThrow(()->new ReservationException(ReservationErrorResult.NO_PAYMONEY));
    }

    public void isNegative(Long amount){
        if(amount < 0){
            throw new ReservationException(ReservationErrorResult.NEGATIVE_PRICE);
        }
    }
}

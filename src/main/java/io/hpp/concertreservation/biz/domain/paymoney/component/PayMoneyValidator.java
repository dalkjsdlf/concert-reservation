package io.hpp.concertreservation.biz.domain.paymoney.component;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyLoadRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PayMoneyValidator
{
    private final IPayMoneyLoadRepository payMoneyLoadRepository;

    public PayMoneyValidator(IPayMoneyLoadRepository payMoneyLoadRepository) {
        this.payMoneyLoadRepository = payMoneyLoadRepository;
    }

    public PayMoney validationOfEnough(Long userId, PayMethod payMethod, Long price){

        Optional<PayMoney> optPayMoney = payMoneyLoadRepository.readByUserIdAndPayMethod(userId, payMethod);
        PayMoney payMoney = optPayMoney.orElseThrow(()->new ReservationException(ReservationErrorResult.NOT_ENOUGH_MONEY));

        Long balance = payMoney.getBalance();

        if(balance.compareTo(price) < 0){
            throw new ReservationException(ReservationErrorResult.NOT_ENOUGH_MONEY);
        }

        return payMoney;
    }
}

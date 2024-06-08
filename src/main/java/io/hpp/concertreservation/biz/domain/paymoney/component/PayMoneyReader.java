package io.hpp.concertreservation.biz.domain.paymoney.component;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyLoadRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@RequiredArgsConstructor
@Component
public class PayMoneyReader {

    private final IPayMoneyLoadRepository payMoneyLoadRepository;
    private final PayMoneyValidator payMoneyValidator;

    public PayMoney readPaymoney(Long userId, PayMethod payMethod){
        return payMoneyValidator.validateExistsPayMoney(userId, payMethod);
    }
}

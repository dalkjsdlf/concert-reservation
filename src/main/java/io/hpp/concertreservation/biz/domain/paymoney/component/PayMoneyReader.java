package io.hpp.concertreservation.biz.domain.paymoney.component;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyLoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PayMoneyReader {

    private final IPayMoneyLoadRepository payMoneyLoadRepository;
    private final PayMoneyValidator payMoneyValidator;
    public PayMoneyReader(@Autowired IPayMoneyLoadRepository payMoneyLoadRepository, PayMoneyValidator payMoneyValidator) {
        this.payMoneyLoadRepository = payMoneyLoadRepository;
        this.payMoneyValidator = payMoneyValidator;
    }

    public PayMoney readPaymoney(Long userId, PayMethod payMethod){
        return payMoneyValidator.validateExistsPayMoney(userId, payMethod);
    }
}

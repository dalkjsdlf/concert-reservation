package io.hpp.concertreservation.biz.domain.paymoney.repository;

import io.hpp.concertreservation.biz.domain.paymoney.infrastructure.IPayMoneyCoreRepository;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayMoneyWriter implements IPayMoneyWriter {
    private final IPayMoneyCoreRepository userPaymentCoreRepository;

    public PayMoneyWriter(@Autowired IPayMoneyCoreRepository userPaymentCoreRepository) {
        this.userPaymentCoreRepository = userPaymentCoreRepository;
    }

    @Override
    public PayMoney writeUserPayment(PayMoney userPayment) {
        return userPaymentCoreRepository.save(userPayment);
    }
}


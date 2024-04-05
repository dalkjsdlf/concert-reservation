package io.hpp.concertreservation.biz.domain.payment.repository;

import io.hpp.concertreservation.biz.domain.paymoney.infrastructure.IPaymoneyCoreRepository;
import io.hpp.concertreservation.biz.domain.paymoney.model.UserPayment;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayMoneyWriter implements IPayMoneyWriter {
    private final IPaymoneyCoreRepository userPaymentCoreRepository;

    public PayMoneyWriter(@Autowired IPaymoneyCoreRepository userPaymentCoreRepository) {
        this.userPaymentCoreRepository = userPaymentCoreRepository;
    }

    @Override
    public UserPayment writeUserPayment(UserPayment userPayment) {
        return userPaymentCoreRepository.save(userPayment);
    }
}


package io.hpp.concertreservation.biz.domain.userpayment.repository;

import io.hpp.concertreservation.biz.domain.userpayment.infrastructure.IUserPaymentCoreRepository;
import io.hpp.concertreservation.biz.domain.userpayment.model.UserPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPaymentWriter implements IUserPaymentWriter{
    private final IUserPaymentCoreRepository userPaymentCoreRepository;

    public UserPaymentWriter(@Autowired IUserPaymentCoreRepository userPaymentCoreRepository) {
        this.userPaymentCoreRepository = userPaymentCoreRepository;
    }

    @Override
    public UserPayment writeUserPayment(UserPayment userPayment) {
        return userPaymentCoreRepository.save(userPayment);
    }
}


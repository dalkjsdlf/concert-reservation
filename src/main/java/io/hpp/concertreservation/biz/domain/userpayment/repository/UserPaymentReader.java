package io.hpp.concertreservation.biz.domain.userpayment.repository;

import io.hpp.concertreservation.biz.domain.userpayment.enumclass.PayMethod;
import io.hpp.concertreservation.biz.domain.userpayment.enumclass.TransactionType;
import io.hpp.concertreservation.biz.domain.userpayment.infrastructure.IUserPaymentCoreRepository;
import io.hpp.concertreservation.biz.domain.userpayment.model.UserPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserPaymentReader implements IUserPaymentReader{

    IUserPaymentCoreRepository userPaymentCoreRepository;

    public UserPaymentReader(@Autowired IUserPaymentCoreRepository userPaymentCoreRepository) {
        this.userPaymentCoreRepository = userPaymentCoreRepository;
    }

    @Override
    public Optional<UserPayment> readById(Long userPaymentId) {
        return userPaymentCoreRepository.findById(userPaymentId);
    }

    @Override
    public Optional<UserPayment> readByUserIdAndPayMethod(Long userId, PayMethod payMethod) {
        return userPaymentCoreRepository.findByUserIdAndPayMethod(userId, payMethod);
    }

    @Override
    public List<UserPayment> readByUserId(Long userId) {
        return userPaymentCoreRepository.findByUserId(userId);
    }
}

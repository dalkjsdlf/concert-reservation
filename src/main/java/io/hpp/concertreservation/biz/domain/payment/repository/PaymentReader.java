package io.hpp.concertreservation.biz.domain.payment.repository;

import io.hpp.concertreservation.biz.domain.paymoney.enumclass.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.infrastructure.IPaymoneyCoreRepository;
import io.hpp.concertreservation.biz.domain.paymoney.model.UserPayment;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PaymentReader implements IPayMoneyReader {

    IPaymoneyCoreRepository userPaymentCoreRepository;

    public PaymentReader(@Autowired IPaymoneyCoreRepository userPaymentCoreRepository) {
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

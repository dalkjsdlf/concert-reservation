package io.hpp.concertreservation.biz.domain.paymoney.repository;

import io.hpp.concertreservation.biz.domain.paymoney.enumclass.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.infrastructure.IPaymoneyCoreRepository;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PayMoneyReader implements IPayMoneyReader {

    IPaymoneyCoreRepository paymentCoreRepository;

    public PayMoneyReader(@Autowired IPaymoneyCoreRepository paymentCoreRepository) {
        this.paymentCoreRepository = paymentCoreRepository;
    }

    @Override
    public Optional<PayMoney> readById(Long userPaymentId) {
        return paymentCoreRepository.findById(userPaymentId);
    }

    @Override
    public Optional<PayMoney> readByUserIdAndPayMethod(Long userId, PayMethod payMethod) {
        return paymentCoreRepository.findByUserIdAndPayMethod(userId, payMethod);
    }

    @Override
    public List<PayMoney> readByUserId(Long userId) {
        return paymentCoreRepository.findByUserId(userId);
    }
}

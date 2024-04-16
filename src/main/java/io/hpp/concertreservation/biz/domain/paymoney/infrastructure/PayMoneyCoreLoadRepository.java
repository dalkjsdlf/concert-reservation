package io.hpp.concertreservation.biz.domain.paymoney.infrastructure;

import io.hpp.concertreservation.biz.domain.paymoney.infrastructure.IPayMoneyJpaRepository;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyLoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@RequiredArgsConstructor
@Component
public class PayMoneyCoreLoadRepository implements IPayMoneyLoadRepository {

    private final IPayMoneyJpaRepository payMoneyJpaRepository;

    @Override
    public Optional<PayMoney> findById(Long userPaymentId) {
        return payMoneyJpaRepository.findById(userPaymentId);
    }

    @Override
    public Optional<PayMoney> findByUserIdAndPayMethod(Long userId, PayMethod payMethod) {
        return payMoneyJpaRepository.findByUserIdAndPayMethod(userId, payMethod);
    }
}

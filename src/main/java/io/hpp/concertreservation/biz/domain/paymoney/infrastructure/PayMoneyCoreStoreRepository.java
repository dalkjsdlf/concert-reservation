package io.hpp.concertreservation.biz.domain.paymoney.infrastructure;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyStoreRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayMoneyCoreStoreRepository implements IPayMoneyStoreRespository {
    private final IPayMoneyJpaRepository payMoneyJpaRepository;

    public PayMoneyCoreStoreRepository(@Autowired IPayMoneyJpaRepository payMoneyJpaRepository) {
        this.payMoneyJpaRepository = payMoneyJpaRepository;
    }

    @Override
    public PayMoney writeUserPayment(PayMoney userPayment) {
        return payMoneyJpaRepository.save(userPayment);
    }
}


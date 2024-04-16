package io.hpp.concertreservation.biz.domain.paymoney.infrastructure;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyStoreRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class PayMoneyCoreStoreRepository implements IPayMoneyStoreRespository {
    private final IPayMoneyJpaRepository payMoneyJpaRepository;

    @Override
    public PayMoney savePayMoney(PayMoney userPayment) {
        return payMoneyJpaRepository.save(userPayment);
    }
}


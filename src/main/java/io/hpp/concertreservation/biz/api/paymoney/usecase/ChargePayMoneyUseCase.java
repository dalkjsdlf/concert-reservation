package io.hpp.concertreservation.biz.api.paymoney.usecase;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyRequestDto;
import io.hpp.concertreservation.biz.domain.paymoney.component.PayMoneyModifier;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class ChargePayMoneyUseCase {

    private final PayMoneyModifier payMoneyModifier;

    public void execute(PayMoneyRequestDto payMoneyRequestDto, Long userId){
        PayMethod payMethod = payMoneyRequestDto.getPayMethod();
        Long price          = payMoneyRequestDto.getPrice();

        payMoneyModifier.charge(userId, payMethod, price);
    }
}

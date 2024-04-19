package io.hpp.concertreservation.biz.api.paymoney.usecase;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyRequestDto;
import io.hpp.concertreservation.biz.domain.paymoney.component.PayMoneyModifier;
import io.hpp.concertreservation.biz.domain.paymoney.component.PayMoneyReader;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Transactional
public class UsePayMoneyUseCase {

    private final PayMoneyReader payMoneyReader;

    private final PayMoneyModifier payMoneyModifier;

    public UsePayMoneyUseCase(@Autowired PayMoneyReader payMoneyReader,
                              @Autowired PayMoneyModifier payMoneyModifier) {
        this.payMoneyReader = payMoneyReader;
        this.payMoneyModifier = payMoneyModifier;
    }

    public void execute(PayMoneyRequestDto payMoneyRequestDto, Long userId){
        PayMethod payMethod = payMoneyRequestDto.getPayMethod();
        Long price          = payMoneyRequestDto.getPrice();

        payMoneyModifier.use(userId, payMethod, price);
    }
}

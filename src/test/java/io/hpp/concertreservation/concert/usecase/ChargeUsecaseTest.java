package io.hpp.concertreservation.concert.usecase;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyRequestDto;
import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyResponseDto;
import io.hpp.concertreservation.biz.api.paymoney.usecase.ChargePayMoneyUseCase;
import io.hpp.concertreservation.biz.api.paymoney.usecase.GetPayMoneyUseCase;
import io.hpp.concertreservation.biz.api.paymoney.usecase.UsePayMoneyUseCase;
import io.hpp.concertreservation.biz.domain.paymoney.component.PayMoneyModifier;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@DisplayName("충전 기능에 대한 테스트")
@Transactional
public class ChargeUsecaseTest {

    @Autowired
    private ChargePayMoneyUseCase chargePayMoneyUseCase;

    @Autowired
    private UsePayMoneyUseCase usePayMoneyUseCase;

    @Autowired
    private PayMoneyModifier payMoneyModifier;

    @Autowired
    private GetPayMoneyUseCase getPayMoneyUseCase;

    private Long userId = 1L;

    @DisplayName("NotNull Test224")
    @Test()
    public void given_whenNotNullCheck_then(){
        //given


        //when


        //then
        assertThat(chargePayMoneyUseCase).isNotNull();
        assertThat(payMoneyModifier).isNotNull();
    }


    @DisplayName("특정 사용자의 충전금액을 충전할 시 충전한 만큼 추가되는지 확인.")
    @Test()
    public void givenUserIdAndChargeAmount_whenChargeAndGet_thenChargedAmount(){
        //given
        Long chargeAmount = 10000L;

        PayMoneyRequestDto payMoneyRequestDto = PayMoneyRequestDto
                .builder()
                .payMethod(PayMethod.CASH)
                .userId(userId)
                .price(chargeAmount)
                .build();
        //when
        chargePayMoneyUseCase.execute(payMoneyRequestDto,userId);

        //then
        PayMoneyResponseDto resultDto = getPayMoneyUseCase.execute(userId, PayMethod.CASH);

        assertThat(resultDto.getUserId()).isEqualTo(userId);
        assertThat(resultDto.getBalance()).isEqualTo(chargeAmount);
    }
}

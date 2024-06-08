package io.hpp.concertreservation.biz.domain.paymoney.component;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyLoadRepository;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyStoreRespository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@RequiredArgsConstructor
@Component
public class PayMoneyModifier {

    private final IPayMoneyStoreRespository payMoneyStoreRespository;

    private final IPayMoneyLoadRepository payMoneyLoadRepository;

    private final PayMoneyValidator payMoneyValidator;

    public void charge(Long userId, PayMethod payMethod,Long amount) {

        try{
            Optional<PayMoney> optPayMoney = payMoneyLoadRepository.findByUserIdAndPayMethod(userId, payMethod);
            PayMoney payMoney = optPayMoney.orElseGet(() -> PayMoney.of(userId, amount, payMethod));
            payMoneyStoreRespository.savePayMoney(payMoney);
        }catch(OptimisticEntityLockException e){
            throw new ReservationException(ReservationErrorResult.IN_PROGRESS);
        }
    }

    public void use(Long userId, PayMethod payMethod, Long amount) {
        /*
         * 잔액이 충분히 있는지 검사
         **/
        PayMoney payMoney = payMoneyValidator.validateEnoughMoney(userId, payMethod, amount);

        /*
        * 현재 잔액 추출
        **/
        Long currentBalance = payMoney.getBalance();

        /*
         * 현재잔액에서 사용금액만큼 차감한다.
         **/
        Long resultBalance  = currentBalance - amount;

        /*
         * 남은 금액이 음수인지 검사
         **/
        payMoneyValidator.isNegative(resultBalance);

        /*
         * 남은 금액을 세팅
         **/
        payMoney.setBalance(resultBalance);

        /*
         * DB에 저장
         **/
        payMoneyStoreRespository.savePayMoney(payMoney);
    }
}

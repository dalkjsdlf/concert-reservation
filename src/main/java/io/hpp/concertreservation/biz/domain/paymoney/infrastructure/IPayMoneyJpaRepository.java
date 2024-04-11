package io.hpp.concertreservation.biz.domain.paymoney.infrastructure;

import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPayMoneyJpaRepository extends JpaRepository<PayMoney,Long> {
    Optional<PayMoney> findByUserIdAndPayMethod(Long userId, PayMethod payMethod);

    List<PayMoney> findByUserId(Long userId);
}

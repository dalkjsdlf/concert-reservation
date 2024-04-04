package io.hpp.concertreservation.biz.domain.userpayment.infrastructure;

import io.hpp.concertreservation.biz.domain.userpayment.enumclass.PayMethod;
import io.hpp.concertreservation.biz.domain.userpayment.enumclass.TransactionType;
import io.hpp.concertreservation.biz.domain.userpayment.model.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserPaymentCoreRepository extends JpaRepository<UserPayment,Long> {
    Optional<UserPayment> findByUserIdAndPayMethod(Long userId, PayMethod payMethod);

    List<UserPayment> findByUserId(Long userId);
}

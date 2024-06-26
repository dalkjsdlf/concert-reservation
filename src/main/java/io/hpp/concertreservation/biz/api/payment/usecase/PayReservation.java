package io.hpp.concertreservation.biz.api.payment.usecase;

import io.hpp.concertreservation.biz.api.payment.dto.PaymentRequestDto;
import io.hpp.concertreservation.biz.domain.payment.component.PaymentCreator;
import io.hpp.concertreservation.biz.domain.payment.component.PaymentModifier;
import io.hpp.concertreservation.biz.domain.payment.component.PaymentValidator;
import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.payment.model.TransactionType;
import io.hpp.concertreservation.biz.domain.paymoney.component.PayMoneyModifier;
import io.hpp.concertreservation.biz.domain.paymoney.component.PayMoneyValidator;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationModifier;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationReader;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Transactional
public class PayReservation {

    private final ReservationReader reservationReader;
    private final PayMoneyModifier payMoneyModifier;
    private final ReservationModifier reservationModifier;
    private final PaymentModifier paymentModifier;

    public void execute(PaymentRequestDto paymentRequestDto,
                 Long userId){

        Long reservationId = paymentRequestDto.getReservationId();
        PayMethod payMethod = paymentRequestDto.getPayMethod();

        /*
        * 예약 정보를 조회.
        * **/
        Reservation reservation = reservationReader.readReservationById(reservationId);

        /*
         * 총 예약 금액을 추출.
         * **/
        Long totalPrice = reservation.getTotalPrice();

        /*
         * 결제 처리하면서 충전 금액을 사용한다.
         * **/
        payMoneyModifier.use(userId, payMethod,totalPrice);

        /*
         * 예약정보에 결제 처리를 한다.
         * **/
        reservationModifier.payReservation(reservation);

        /*
         * 결제내역 저장
         * **/
        Payment payment = PaymentCreator.create(userId, TransactionType.USE,totalPrice, LocalDateTime.now(), reservationId);
        paymentModifier.addPaymentHistory(payment);

    }
}

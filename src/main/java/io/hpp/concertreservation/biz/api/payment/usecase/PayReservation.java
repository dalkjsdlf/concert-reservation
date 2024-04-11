package io.hpp.concertreservation.biz.api.payment.usecase;

import io.hpp.concertreservation.biz.api.payment.dto.PaymentRequestDto;
import io.hpp.concertreservation.biz.domain.payment.component.PaymentCreator;
import io.hpp.concertreservation.biz.domain.payment.component.PaymentModifier;
import io.hpp.concertreservation.biz.domain.payment.component.PaymentValidator;
import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.payment.model.TransactionType;
import io.hpp.concertreservation.biz.domain.paymoney.component.PayMoneyValidator;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMoney;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationModifier;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationReader;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Transactional()
public class PayReservation {

    private final ReservationReader reservationReader;
    private final PayMoneyValidator payMoneyValidator;
    private final ReservationModifier reservationModifier;

    private final PaymentModifier paymentModifier;


    public PayReservation(ReservationReader reservationReader,
                          PayMoneyValidator payMoneyValidator,
                          ReservationModifier reservationModifier, PaymentModifier paymentModifier) {
        this.reservationReader = reservationReader;
        this.payMoneyValidator = payMoneyValidator;
        this.reservationModifier = reservationModifier;
        this.paymentModifier = paymentModifier;
    }

    void execute(PaymentRequestDto paymentRequestDto,
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
         * 현재 결제수단에 충전된 금액이 결제하기에 부족한지 체크한다.
         * 부족하다면 예외
         * **/
        PayMoney payMoney = payMoneyValidator.validationOfEnough(userId, payMethod, totalPrice);

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

package io.hpp.concertreservation.biz.domain.payment.service;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyRequestDto;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.reservation.repository.IReservationReader;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatReader;
import io.hpp.concertreservation.biz.domain.paymoney.enumclass.PayMethod;
import io.hpp.concertreservation.biz.domain.paymoney.repository.IPayMoneyReader;
import io.hpp.concertreservation.biz.domain.paymoney.service.IPayMoneyService;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PayMoneyService implements IPayMoneyService {

    private final IPayMoneyReader userPaymentReader;
    private final IReservationReader reservationReader;

    private final ISeatReader seatReader;
    public PayMoneyService(@Autowired IPayMoneyReader userPaymentReader, IReservationReader reservationReader, ISeatReader seatReader) {
        this.userPaymentReader = userPaymentReader;
        this.reservationReader = reservationReader;
        this.seatReader = seatReader;
    }


    @Override
    public void chargeMoney(PayMoneyRequestDto chargeDto) {

    }
}

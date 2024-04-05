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
    public void useMoney(PayMoneyRequestDto useDto) {

        PayMethod payMethod = useDto.getPayMethod();
        Long userId         = useDto.getUserId();
        Long price          = useDto.getPrice();
        Long reserveId      = useDto.getReserveId();

        Optional<Reservation> optReservation = reservationReader.readReservationById(reserveId);
        Reservation reservation = optReservation.orElseThrow(()->new ReservationException(ReservationErrorResult.NO_RESERVATION));

        Long totalPrice = reservation.getTotalPrice();

        Optional<UserPayment> optUserPayment = userPaymentReader.readByUserIdAndPayMethod(userId, payMethod);
        UserPayment userPayment = optUserPayment.orElseThrow(()->new ReservationException(ReservationErrorResult.NOT_ENOUGH_MONEY));

        Long result = userPayment.getBalance() - totalPrice;

        if(result < 0){
            throw new ReservationException(ReservationErrorResult.NOT_ENOUGH_MONEY);
        }

        /*
        * 차감된 잔액 세팅
        * **/
        userPayment.setBalance(result);


//        UserPayment userPayment = null;
//        if(optUserPayment.isPresent()){
//            userPayment = optUserPayment.get();
//        }else{
//            userPayment = UserPayment.of(
//                    userId,
//
//            )
//        }


    }

    @Override
    public void chargeMoney(PayMoneyRequestDto chargeDto) {

    }
}

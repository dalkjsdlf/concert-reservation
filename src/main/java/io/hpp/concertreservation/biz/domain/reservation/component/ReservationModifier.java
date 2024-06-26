package io.hpp.concertreservation.biz.domain.reservation.component;

import io.hpp.concertreservation.biz.domain.reservation.model.PaymentStatus;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.reservation.repository.IReservationLoadRepository;
import io.hpp.concertreservation.biz.domain.reservation.repository.IReservationStoreRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class ReservationModifier{

    private final IReservationStoreRepository reservationStoreRepository;
    private final IReservationLoadRepository reservationLoadRepository;

    public Reservation addReservation(Reservation reservation){

        /**
         * #TODO 유효성 검증 로직 추가
         * */


        /**
         * 예약정보 저장
         * */
        return reservationStoreRepository.saveReservation(reservation);
    }

    public void removeReservation(Long reservationId){

        /**
         * #TODO 유효성 검증 로직 추가
         * */
        if(reservationLoadRepository.findReservationById(reservationId).isPresent() == false){
            throw new ReservationException(ReservationErrorResult.FAIL_DELETE_NO_RESERVATION);
        }

        /**
         * 예약정보 삭제
         * */
        reservationStoreRepository.deleteById(reservationId);
    }

    public void payReservation(Reservation reservation){

        /**
         * #TODO 유효성 검증 로직 추가
         * */


        /**
         *
         * */
        reservation.setStatus(PaymentStatus.COMPLETE);

        /**
         * 예약정보 저장
         * */
        reservationStoreRepository.saveReservation(reservation);
    }

}